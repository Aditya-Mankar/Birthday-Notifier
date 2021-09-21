package com.birthdaynotifier.service;

import com.birthdaynotifier.constant.Constant;
import com.birthdaynotifier.exception.BadRequestException;
import com.birthdaynotifier.model.Birthday;
import com.birthdaynotifier.model.User;
import com.birthdaynotifier.utility.Utility;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.stereotype.Service;

@Service
public class RequestValidator implements IRequestValidator{

    @Override
    public void validateAddBirthdayRequest(Birthday birthday) {
        if(Utility.checkIfNullOrEmpty(birthday.getEmailId()))
            throw new BadRequestException(Constant.error_email_id_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getName()))
            throw new BadRequestException(Constant.error_name_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getBirthDate()))
            throw new BadRequestException(Constant.error_birth_date_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getBirthMonth()))
            throw new BadRequestException(Constant.error_birth_month_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getRemindBeforeDays()))
            throw new BadRequestException(Constant.error_remind_before_days_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getRemindDate()))
            throw new BadRequestException(Constant.error_remind_date_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getRemindMonth()))
            throw new BadRequestException(Constant.error_remind_month_null_or_empty);
    }

    @Override
    public void validateUpdateBirthdayRequest(Birthday birthday) {
        if(Utility.checkIfNullOrEmpty(birthday.getId()))
            throw new BadRequestException(Constant.error_id_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getEmailId()))
            throw new BadRequestException(Constant.error_email_id_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getName()))
            throw new BadRequestException(Constant.error_name_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getBirthDate()))
            throw new BadRequestException(Constant.error_birth_date_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getBirthMonth()))
            throw new BadRequestException(Constant.error_birth_month_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getRemindBeforeDays()))
            throw new BadRequestException(Constant.error_remind_before_days_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getRemindDate()))
            throw new BadRequestException(Constant.error_remind_date_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getRemindMonth()))
            throw new BadRequestException(Constant.error_remind_month_null_or_empty);
    }

    @Override
    public void validateAddUserRequest(User user) {
        if(Utility.checkIfNullOrEmpty(user.getEmailId()))
            throw new BadRequestException(Constant.error_email_id_null_or_empty);

        if(Utility.checkIfNullOrEmpty(user.getUsername()))
            throw new BadRequestException(Constant.error_username_null_or_empty);

        if(Utility.checkIfNullOrEmpty(user.getPassword()))
            throw new BadRequestException(Constant.error_password_null_or_empty);
    }

    @Override
    public void validateInsertBirthdayRequest(Birthday birthday) {
        if(Utility.checkIfNullOrEmpty(birthday.getEmailId()))
            throw new BadRequestException(Constant.error_email_id_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getName()))
            throw new BadRequestException(Constant.error_name_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getBirthDate()))
            throw new BadRequestException(Constant.error_birth_date_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getBirthMonth()))
            throw new BadRequestException(Constant.error_birth_month_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getRemindBeforeDays()))
            throw new BadRequestException(Constant.error_remind_before_days_null_or_empty);
    }

    @Override
    public void validateModifyBirthdayRequest(Birthday birthday) {
        if(Utility.checkIfNullOrEmpty(birthday.getId()))
            throw new BadRequestException(Constant.error_id_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getEmailId()))
            throw new BadRequestException(Constant.error_email_id_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getName()))
            throw new BadRequestException(Constant.error_name_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getBirthDate()))
            throw new BadRequestException(Constant.error_birth_date_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getBirthMonth()))
            throw new BadRequestException(Constant.error_birth_month_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getRemindBeforeDays()))
            throw new BadRequestException(Constant.error_remind_before_days_null_or_empty);
    }

    @Override
    public void validateUserEmailIdRequest(User user) {
        if(Utility.checkIfNullOrEmpty(user.getEmailId()))
            throw new BadRequestException(Constant.error_email_id_null_or_empty);

        if(Utility.checkIfNullOrEmpty(user.getSecretCode()))
            throw new BadRequestException(Constant.error_code_null_or_empty);
    }

    @Override
    public void validateUserUpdatePasswordRequest(User user) {
        if(Utility.checkIfNullOrEmpty(user.getEmailId()))
            throw new BadRequestException(Constant.error_email_id_null_or_empty);

        if(Utility.checkIfNullOrEmpty(user.getPassword()))
            throw new BadRequestException(Constant.error_password_null_or_empty);

        if(Utility.checkIfNullOrEmpty(user.getSecretCode()))
            throw new BadRequestException(Constant.error_code_null_or_empty);
    }

    @Override
    public void validateLoginRequest(User user) {
        if(Utility.checkIfNullOrEmpty(user.getUsername()))
            throw new BadRequestException(Constant.error_username_null_or_empty);

        if(Utility.checkIfNullOrEmpty(user.getPassword()))
            throw new BadRequestException(Constant.error_password_null_or_empty);
    }

}
