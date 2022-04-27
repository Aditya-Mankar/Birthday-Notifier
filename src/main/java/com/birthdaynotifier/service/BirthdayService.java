package com.birthdaynotifier.service;

import com.birthdaynotifier.constant.Constants;
import com.birthdaynotifier.constant.LoggingConstants;
import com.birthdaynotifier.exception.BadRequestException;
import com.birthdaynotifier.exception.CustomException;
import com.birthdaynotifier.model.Birthday;
import com.birthdaynotifier.repository.BirthdayRepository;
import com.birthdaynotifier.utility.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class BirthdayService {

    private final RequestValidatorService requestValidator;
    private final BirthdayRepository birthdayRepository;

    public BirthdayService(RequestValidatorService requestValidator, BirthdayRepository birthdayRepository) {
        this.requestValidator = requestValidator;
        this.birthdayRepository = birthdayRepository;
    }

    Logger logger = LoggerFactory.getLogger(BirthdayService.class);

    public ResponseEntity<Object> getBirthdaysByEmailId(String emailId) {
        logger.info(LoggingConstants.message_get_birthdays + emailId);
        try {
            requestValidator.validateEmailId(emailId);

            List<Birthday> birthdayList = birthdayRepository.getBirthdaysByEmailId(emailId);

            logger.info(LoggingConstants.success_get_birthdays + emailId);
            return ResponseEntity.ok().body(birthdayList);
        } catch (BadRequestException e) {
            logger.error(LoggingConstants.fail_get_birthdays + emailId + Constants.error + e.getErrorMessage());
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        } catch (CustomException e) {
            logger.error(LoggingConstants.fail_get_birthdays + emailId + Constants.error + e.getErrorMessage());
            return ResponseEntity.internalServerError().body(e.getErrorMessage());
        } catch (Exception e) {
            logger.error(LoggingConstants.fail_get_birthdays + emailId + Constants.error + e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


    public ResponseEntity<String> insertBirthday(Birthday birthday) {
        logger.info(LoggingConstants.message_insert_birthday + birthday.getEmailId() + Constants.for_name + birthday.getName());
        try {
            requestValidator.validateInsertBirthdayRequest(birthday);

            Date newDate = new Date(0, birthday.getBirthMonth() - 1, birthday.getBirthDate());
            Instant now = newDate.toInstant();
            Instant daysAgo = now.minus(Duration.ofDays(birthday.getRemindBeforeDays()));
            Date dateDaysAgo = Date.from(daysAgo);
            int month = dateDaysAgo.getMonth() + 1;
            int date = dateDaysAgo.getDate();

            birthday.setRemindMonth(month);
            birthday.setRemindDate(date);
            birthday.setId(Utility.generateUUID(3));
            birthday.setCreatedAt(Instant.now().toString());

            birthdayRepository.addNewBirthday(birthday);

            logger.info(LoggingConstants.success_insert_birthday + birthday.getEmailId() + Constants.for_name + birthday.getName());
            return ResponseEntity.ok().body(LoggingConstants.success_insert_birthday + birthday.getEmailId());
        } catch (BadRequestException e) {
            logger.error(LoggingConstants.fail_insert_birthday + birthday.getEmailId() + Constants.for_name + birthday.getName() + Constants.error + e.getErrorMessage());
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        } catch (CustomException e) {
            logger.error(LoggingConstants.fail_insert_birthday + birthday.getEmailId() + Constants.for_name + birthday.getName() + Constants.error + e.getErrorMessage());
            return ResponseEntity.internalServerError().body(e.getErrorMessage());
        }  catch (Exception e) {
            logger.error(LoggingConstants.fail_insert_birthday + birthday.getEmailId() + Constants.for_name + birthday.getName() + Constants.error + e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }


    }

    public ResponseEntity<String> modifyBirthday(Birthday birthday) {
        logger.info(LoggingConstants.message_update_birthday + birthday.getEmailId() + Constants.for_name + birthday.getName());
        try {
            requestValidator.validateModifyBirthdayRequest(birthday);

            Date newDate = new Date(0, birthday.getBirthMonth() - 1, birthday.getBirthDate());
            Instant now = newDate.toInstant();
            Instant daysAgo = now.minus(Duration.ofDays(birthday.getRemindBeforeDays()));
            Date dateDaysAgo = Date.from(daysAgo);
            int month = dateDaysAgo.getMonth() + 1;
            int date = dateDaysAgo.getDate();

            birthday.setRemindMonth(month);
            birthday.setRemindDate(date);
            birthday.setUpdatedAt(Instant.now().toString());

            birthdayRepository.updateBirthday(birthday);

            logger.info(LoggingConstants.success_update_birthday + birthday.getEmailId() + Constants.for_name + birthday.getName());
            return ResponseEntity.ok().body(LoggingConstants.success_update_birthday + birthday.getEmailId());
        } catch (BadRequestException e) {
            logger.error(LoggingConstants.fail_update_birthday + birthday.getEmailId() + Constants.for_name + birthday.getName() + Constants.error + e.getErrorMessage());
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        } catch (CustomException e) {
            logger.error(LoggingConstants.fail_update_birthday + birthday.getEmailId() + Constants.for_name + birthday.getName() + Constants.error + e.getErrorMessage());
            return ResponseEntity.internalServerError().body(e.getErrorMessage());
        }  catch (Exception e) {
            logger.error(LoggingConstants.fail_update_birthday + birthday.getEmailId() + Constants.for_name + birthday.getName() + Constants.error + e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    public ResponseEntity<String> deleteBirthday(int id) {
        logger.info(LoggingConstants.message_delete_birthday + id);
        try {
            requestValidator.validateDeleteBirthdayRequest(id);

            birthdayRepository.deleteBirthday(id);

            logger.info(LoggingConstants.success_delete_birthday + id);
            return ResponseEntity.ok().body(LoggingConstants.success_delete_birthday + id);
        } catch (BadRequestException e) {
            logger.error(LoggingConstants.fail_delete_birthday + id);
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        } catch (CustomException e) {
            logger.error(LoggingConstants.fail_delete_birthday + id);
            return ResponseEntity.internalServerError().body(e.getErrorMessage());
        }  catch (Exception e) {
            logger.error(LoggingConstants.fail_delete_birthday + id);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }
}
