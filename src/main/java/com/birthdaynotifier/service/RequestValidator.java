package com.birthdaynotifier.service;

import com.birthdaynotifier.exception.BadRequestException;
import com.birthdaynotifier.model.Birthday;
import com.birthdaynotifier.model.User;
import com.birthdaynotifier.utility.Utility;
import org.springframework.stereotype.Service;

@Service
public class RequestValidator implements IRequestValidator{

    @Override
    public void validateAddBirthdayRequest(Birthday birthday) throws BadRequestException {
        if(Utility.checkIfNullOrEmpty(birthday.getEmailId())) {
            throw new BadRequestException("Email id cannot be null or empty");
        }

        if(Utility.checkIfNullOrEmpty(birthday.getName())) {
            throw new BadRequestException("Name cannot be null or empty");
        }

        if(Utility.checkIfNullOrEmpty(birthday.getBirthDate())) {
            throw new BadRequestException("Birth date cannot be null or empty");
        }

        if(Utility.checkIfNullOrEmpty(birthday.getDate())) {
            throw new BadRequestException("Date cannot be null or empty");
        }

        if(Utility.checkIfNullOrEmpty(birthday.getMonth())) {
            throw new BadRequestException("Month cannot be null or empty");
        }
    }

    @Override
    public void validateUpdateBirthdayRequest(Birthday birthday) {
        if(Utility.checkIfNullOrEmpty(birthday.getId())) {
            throw new BadRequestException("Id cannot be null or empty");
        }

        if(Utility.checkIfNullOrEmpty(birthday.getEmailId())) {
            throw new BadRequestException("Email id cannot be null or empty");
        }

        if(Utility.checkIfNullOrEmpty(birthday.getName())) {
            throw new BadRequestException("Name cannot be null or empty");
        }

        if(Utility.checkIfNullOrEmpty(birthday.getBirthDate())) {
            throw new BadRequestException("Birth date cannot be null or empty");
        }

        if(Utility.checkIfNullOrEmpty(birthday.getDate())) {
            throw new BadRequestException("Date cannot be null or empty");
        }

        if(Utility.checkIfNullOrEmpty(birthday.getMonth())) {
            throw new BadRequestException("Month cannot be null or empty");
        }
    }

    @Override
    public void validateAddUserRequest(User user) {
        if(Utility.checkIfNullOrEmpty(user.getEmailId())) {
            throw new BadRequestException("EmailId cannot be null or empty");
        }

        if(Utility.checkIfNullOrEmpty(user.getUsername())) {
            throw new BadRequestException("Username cannot be null or empty");
        }

        if(Utility.checkIfNullOrEmpty(user.getPassword())) {
            throw new BadRequestException("Password cannot be null or empty");
        }
    }

}
