package com.birthdaynotifier.service;

import com.birthdaynotifier.constant.Constant;
import com.birthdaynotifier.exception.CustomException;
import com.birthdaynotifier.model.Birthday;
import com.birthdaynotifier.model.Mail;
import com.birthdaynotifier.respository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MailService implements IMailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public void sendHtmlMail(Mail mail, String template) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariables(mail.getProps());

        String html = templateEngine.process(template, context);

        helper.setFrom(mail.getMailFrom());
        helper.setTo(mail.getMailTo());
        helper.setSubject(mail.getSubject());
        helper.setText(html, true);

        mailSender.send(message);
    }

    @Override
    public String sendMail(Birthday birthday) throws MessagingException {

        String sendFrom = "";
        String sendTo = birthday.getEmailId();
        String subject = "Birthday reminder for " + birthday.getName();

        Map<String, Object> props = new HashMap<>();
        props.put("name", birthday.getName());
        props.put("birthdate", birthday.getBirthDate());

        Mail mail = new Mail(sendFrom, sendTo, subject, props);

        sendHtmlMail(mail, "birthday-reminder-template");

        return "Mail sent successfully";
    }

    @Override
    public ResponseEntity<?> sendCodeForUser(String emailId) {
        try {
            if(!userRepository.checkIfUserExistsByEmailId(emailId))
                throw new CustomException(Constant.error_no_user_exists_with_email_id);

            String code = userRepository.fetchCode(emailId);

            String sendFrom = "addy007patil@gmail.com";
            String sendTo = emailId;
            String subject = "Secret code for Birthday reminder service";

            Map<String, Object> props = new HashMap<>();
            props.put("code", code);

            Mail mail = new Mail(sendFrom, sendTo, subject, props);

            sendHtmlMail(mail, "send-code-template");

            return ResponseEntity.ok().body(Constant.success_mail_code_sent);
        } catch (CustomException e) {
            return ResponseEntity.internalServerError().body(e.getErrorMessage());
        } catch (MessagingException e) {
            return ResponseEntity.internalServerError().body(Constant.error_mail_code_not_sent);
        }
    }

}
