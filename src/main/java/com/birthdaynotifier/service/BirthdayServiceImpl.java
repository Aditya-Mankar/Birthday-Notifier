package com.birthdaynotifier.service;

import com.birthdaynotifier.constant.Constant;
import com.birthdaynotifier.exception.BadRequestException;
import com.birthdaynotifier.exception.CustomException;
import com.birthdaynotifier.model.Birthday;
import com.birthdaynotifier.respository.IBirthdayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BirthdayServiceImpl implements IBirthdayService {

    @Autowired
    private IBirthdayRepository birthdayRepository;

    @Autowired
    private IRequestValidator requestValidator;

    @Override
    public List<Birthday> getAllBirthdays() {
        return birthdayRepository.getAllBirthdays();
    }

    @Override
    public ResponseEntity<?> getBirthdaysByEmailId(String emailId) {
        try {
            birthdayRepository.validateEmailId(emailId);

            List<Birthday> birthdayList = birthdayRepository.getBirthdaysByEmailId(emailId);

            return ResponseEntity.ok().headers(new HttpHeaders()).body(birthdayList);
        } catch (CustomException e) {
            return ResponseEntity.internalServerError().body(e.getErrorMessage());
        }
    }

    @Override
    public ResponseEntity<?> addNewBirthday(Birthday birthday) {
        try {
            requestValidator.validateAddBirthdayRequest(birthday);

            birthday.setId(generateUUID(3));
            birthday.setCreatedAt(Instant.now().toString());

            birthdayRepository.addNewBirthday(birthday);

            return ResponseEntity.ok().headers(new HttpHeaders()).body(Constant.success_birthday_added);
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateBirthday(Birthday birthday) {
        try {
            requestValidator.validateUpdateBirthdayRequest(birthday);

            birthdayRepository.checkBirthdayExists(Integer.parseInt(birthday.getId()));

            birthday.setUpdatedAt(Instant.now().toString());

            birthdayRepository.updateBirthday(birthday);

            return ResponseEntity.ok().headers(new HttpHeaders()).body(Constant.success_birthday_updated);
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        } catch (CustomException e) {
            return ResponseEntity.internalServerError().body(e.getErrorMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteBirthday(int id) {
        try {
            if(id == 0)
                throw new BadRequestException(Constant.error_id_null_or_empty);

            birthdayRepository.checkBirthdayExists(id);

            birthdayRepository.deleteBirthday(id);

            return ResponseEntity.ok().headers(new HttpHeaders()).body(Constant.success_birthday_deleted);
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        } catch (CustomException e) {
            return ResponseEntity.internalServerError().body(e.getErrorMessage());
        }
    }

    @Override
    public ResponseEntity<?> insertBirthday(Birthday birthday) {
        try {
            requestValidator.validateInsertBirthdayRequest(birthday);

            String tempDate = birthday.getBirthDate();
            SimpleDateFormat formatter = new SimpleDateFormat(Constant.date_format);
            Date formattedDate = formatter.parse(tempDate);
            Instant now = formattedDate.toInstant();
            Instant daysAgo = now.minus(Duration.ofDays(birthday.getRemindBeforeDays()));
            Date dateDaysAgo = Date.from(daysAgo);

            int month = dateDaysAgo.getMonth() + 1;
            int date = dateDaysAgo.getDate();

            birthday.setMonth(month);
            birthday.setDate(date);

            addNewBirthday(birthday);

            return ResponseEntity.ok().headers(new HttpHeaders()).body(Constant.success_birthday_added);
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        } catch (CustomException e) {
            return ResponseEntity.internalServerError().body(e.getErrorMessage());
        } catch (ParseException e) {
            return ResponseEntity.internalServerError().body(Constant.error_birthdate_invalid_format);
        }
    }

    @Override
    public ResponseEntity<?> modifyBirthday(Birthday birthday) {
        try {
            requestValidator.validateModifyBirthdayRequest(birthday);

            String tempDate = birthday.getBirthDate();
            SimpleDateFormat formatter = new SimpleDateFormat(Constant.date_format);
            Date formattedDate = formatter.parse(tempDate);
            Instant now = formattedDate.toInstant();
            Instant daysAgo = now.minus(Duration.ofDays(birthday.getRemindBeforeDays()));
            Date dateDaysAgo = Date.from(daysAgo);

            int month = dateDaysAgo.getMonth() + 1;
            int date = dateDaysAgo.getDate();

            birthday.setMonth(month);
            birthday.setDate(date);

            updateBirthday(birthday);

            return ResponseEntity.ok().headers(new HttpHeaders()).body(Constant.success_birthday_updated);
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        } catch (CustomException e) {
            return ResponseEntity.internalServerError().body(e.getErrorMessage());
        } catch (ParseException e) {
            return ResponseEntity.internalServerError().body(Constant.error_birthdate_invalid_format);
        }
    }

    public static String generateUUID(int length) {
        UUID randomUUID = UUID.randomUUID();
        String randomString = randomUUID.toString().replaceAll("[^0-9]", "");
        return randomString.substring(0, length);
    }

}
