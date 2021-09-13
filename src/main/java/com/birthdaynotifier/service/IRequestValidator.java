package com.birthdaynotifier.service;

import com.birthdaynotifier.exception.BadRequestException;
import com.birthdaynotifier.model.Birthday;

public interface IRequestValidator {

    public void validateAddBirthdayRequest(Birthday birthday) throws BadRequestException;

    public void validateUpdateBirthdayRequest(Birthday birthday);
}
