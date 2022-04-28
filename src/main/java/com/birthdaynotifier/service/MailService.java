package com.birthdaynotifier.service;

import com.birthdaynotifier.constant.Constants;
import com.birthdaynotifier.constant.LoggingConstants;
import com.birthdaynotifier.exception.BadRequestException;
import com.birthdaynotifier.exception.CustomException;
import com.birthdaynotifier.model.Birthday;
import com.birthdaynotifier.model.Mail;
import com.birthdaynotifier.repository.UserRepository;
import com.birthdaynotifier.utility.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailService {

    private final RequestValidatorService requestValidator;
    private final UserRepository userRepository;
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    public MailService(RequestValidatorService requestValidator, UserRepository userRepository, JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.requestValidator = requestValidator;
        this.userRepository = userRepository;
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    Logger logger = LoggerFactory.getLogger(MailService.class);

    public ResponseEntity<String> sendCodeForUser(String emailId) {
        logger.info(LoggingConstants.message_send_code_mail + emailId);
        try {
            requestValidator.validateSendCodeRequest(emailId);

            String code = userRepository.fetchCode(emailId);
            Map<String, Object> props = new HashMap<>();
            props.put(Constants.code, code);

            Mail mail = Mail.builder()
                    .setMailFrom(Constants.mail_id)
                    .setMailTo(emailId)
                    .setSubject(Constants.mail_code_subject)
                    .setProps(props)
                    .build();

            sendHtmlMail(mail, Constants.template_send_code);

            logger.info(LoggingConstants.success_send_code_mail + emailId);
            return ResponseEntity.ok().body(LoggingConstants.success_send_code_mail + emailId);
        } catch (BadRequestException e) {
            logger.error(LoggingConstants.fail_send_code_mail + emailId + Constants.error + e.getErrorMessage());
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        } catch (CustomException e) {
            logger.error(LoggingConstants.fail_send_code_mail + emailId + Constants.error + e.getErrorMessage());
            return ResponseEntity.internalServerError().body(e.getErrorMessage());
        } catch (Exception e) {
            logger.error(LoggingConstants.fail_send_code_mail + emailId + Constants.error + e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    public String sendBirthdayMail(Birthday birthday) {
        logger.info(LoggingConstants.message_send_birthday_mail + birthday.getEmailId());
        try {
            Map<String, Object> props = new HashMap<>();
            props.put(Constants.name, birthday.getName());
            props.put(Constants.birthdate, birthday.getBirthDate());
            props.put(Constants.birthmonth, Utility.determineMonthFromNum(birthday.getBirthMonth()));

            Mail mail = Mail.builder()
                    .setMailFrom(Constants.mail_id)
                    .setMailTo(birthday.getEmailId())
                    .setSubject(Constants.mail_birthday_subject + birthday.getName())
                    .setProps(props)
                    .build();

            sendHtmlMail(mail, Constants.template_birthday_reminder);

            logger.info(LoggingConstants.success_send_birthday_mail + birthday.getEmailId());
            return LoggingConstants.success_send_birthday_mail + birthday.getEmailId();
        } catch (MessagingException e) {
            logger.error(LoggingConstants.fail_send_birthday_mail + birthday.getEmailId() + Constants.error + e.getMessage());
            return LoggingConstants.fail_send_birthday_mail + birthday.getEmailId() + Constants.error + e.getMessage();
        } catch (Exception e) {
            logger.error(LoggingConstants.fail_send_birthday_mail + birthday.getEmailId() + Constants.error + e.getMessage());
            return LoggingConstants.fail_send_birthday_mail + birthday.getEmailId() + Constants.error + e.getMessage();
        }
    }

    private void sendHtmlMail(Mail mail, String template) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariables(mail.getProps());

        String html = templateEngine.process(template, context);

        helper.setFrom(mail.getMailFrom());
        helper.setTo(mail.getMailTo());
        helper.setSubject(mail.getSubject());
        helper.setText(html, true);

        mailSender.send(message);
    }

}
