package com.birthdaynotifier.service;

import com.birthdaynotifier.exception.BadRequestException;
import com.birthdaynotifier.model.Birthday;
import org.springframework.stereotype.Service;

@Service
public class RequestValidator implements IRequestValidator{

    @Override
    public void validateAddBirthdayRequest(Birthday birthday) throws BadRequestException {
        if(checkIfNullOrEmpty(birthday.getEmailId())) {
            throw new BadRequestException("Email id cannot be null or empty for inserting birthday");
        }

        if(checkIfNullOrEmpty(birthday.getName())) {
            throw new BadRequestException("Name cannot be null or empty for inserting birthday");
        }

        if(checkIfNullOrEmpty(birthday.getBirthDate())) {
            throw new BadRequestException("Birth date cannot be null or empty for inserting birthday");
        }

        if(checkIfNullOrEmpty(birthday.getDate())) {
            throw new BadRequestException("Date cannot be null or empty for inserting birthday");
        }

        if(checkIfNullOrEmpty(birthday.getMonth())) {
            throw new BadRequestException("Month cannot be null or empty for inserting birthday");
        }
    }

    @Override
    public void validateUpdateBirthdayRequest(Birthday birthday) {
        if(checkIfNullOrEmpty(birthday.getId())) {
            throw new BadRequestException("Id cannot be null or empty for inserting birthday");
        }

        if(checkIfNullOrEmpty(birthday.getEmailId())) {
            throw new BadRequestException("Email id cannot be null or empty for inserting birthday");
        }

        if(checkIfNullOrEmpty(birthday.getName())) {
            throw new BadRequestException("Name cannot be null or empty for inserting birthday");
        }

        if(checkIfNullOrEmpty(birthday.getBirthDate())) {
            throw new BadRequestException("Birth date cannot be null or empty for inserting birthday");
        }

        if(checkIfNullOrEmpty(birthday.getDate())) {
            throw new BadRequestException("Date cannot be null or empty for inserting birthday");
        }

        if(checkIfNullOrEmpty(birthday.getMonth())) {
            throw new BadRequestException("Month cannot be null or empty for inserting birthday");
        }
    }

    public boolean checkIfNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public boolean checkIfNullOrEmpty(int num) {
        return num == 0;
    }

}
