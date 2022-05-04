package com.birthdaynotifier.service;

import com.birthdaynotifier.constant.Constants;
import com.birthdaynotifier.exception.BadRequestException;
import com.birthdaynotifier.exception.CustomException;
import com.birthdaynotifier.model.Birthday;
import com.birthdaynotifier.model.User;
import com.birthdaynotifier.repository.BirthdayRepository;
import com.birthdaynotifier.repository.UserRepository;
import com.birthdaynotifier.utility.Utility;
import org.springframework.stereotype.Service;

@Service
public class RequestValidatorService {

    private final BirthdayRepository birthdayRepository;
    private final UserRepository userRepository;

    public RequestValidatorService(BirthdayRepository birthdayRepository, UserRepository userRepository) {
        this.birthdayRepository = birthdayRepository;
        this.userRepository = userRepository;
    }

    public void validateEmailId(String emailId) {
        if(Utility.checkIfNullOrEmpty(emailId))
            throw new BadRequestException(Constants.error_email_id_null_or_empty);

        if(userRepository.validateUserByEmailId(emailId))
            throw new CustomException(Constants.error_user_invalid_email_id);
    }

    public void validateInsertBirthdayRequest(Birthday birthday) {
        if(Utility.checkIfNullOrEmpty(birthday.getEmailId()))
            throw new BadRequestException(Constants.error_email_id_null_or_empty);

        if(userRepository.validateUserByEmailId(birthday.getEmailId()))
            throw new CustomException(Constants.error_user_invalid_email_id);

        if(Utility.checkIfNullOrEmpty(birthday.getName()))
            throw new BadRequestException(Constants.error_name_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getBirthDate()))
            throw new BadRequestException(Constants.error_birth_date_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getBirthMonth()))
            throw new BadRequestException(Constants.error_birth_month_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getRemindBeforeDays()))
            throw new BadRequestException(Constants.error_remind_before_days_null_or_empty);
    }

    public void validateModifyBirthdayRequest(Birthday birthday) {
        if(Utility.checkIfNullOrEmpty(birthday.getId()))
            throw new BadRequestException(Constants.error_id_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getEmailId()))
            throw new BadRequestException(Constants.error_email_id_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getName()))
            throw new BadRequestException(Constants.error_name_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getBirthDate()))
            throw new BadRequestException(Constants.error_birth_date_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getBirthMonth()))
            throw new BadRequestException(Constants.error_birth_month_null_or_empty);

        if(Utility.checkIfNullOrEmpty(birthday.getRemindBeforeDays()))
            throw new BadRequestException(Constants.error_remind_before_days_null_or_empty);

        if(!birthdayRepository.checkBirthdayExists(Integer.parseInt(birthday.getId())))
            throw new CustomException(Constants.error_no_birthday_with_id);
    }

    public void validateDeleteBirthdayRequest(int id) {
        if(Utility.checkIfNullOrEmpty(id))
            throw new BadRequestException(Constants.error_id_null_or_empty);

        if(!birthdayRepository.checkBirthdayExists(id))
            throw new CustomException(Constants.error_no_birthday_with_id);
    }

    public void validateFetchUserRequest(String username) {
        if (Utility.checkIfNullOrEmpty(username))
            throw new BadRequestException(Constants.error_username_null_or_empty);

        if(userRepository.validateUserByUsername(username))
            throw new CustomException(Constants.error_user_invalid_username);
    }

    public void validateInsertUserRequest(User user) {
        if(Utility.checkIfNullOrEmpty(user.getEmailId()))
            throw new BadRequestException(Constants.error_email_id_null_or_empty);

        if(Utility.checkIfNullOrEmpty(user.getUsername()))
            throw new BadRequestException(Constants.error_username_null_or_empty);

        if(Utility.checkIfNullOrEmpty(user.getPassword()))
            throw new BadRequestException(Constants.error_password_null_or_empty);

        if(!userRepository.validateUserByUsername(user.getUsername()))
            throw new CustomException(Constants.error_user_already_exists_username);

        if(!userRepository.validateUserByEmailId(user.getEmailId()))
            throw new CustomException(Constants.error_user_already_exists_email_id);
    }

    public void validateUserEmailIdRequest(User user) {
        if(Utility.checkIfNullOrEmpty(user.getEmailId()))
            throw new BadRequestException(Constants.error_email_id_null_or_empty);

        if(Utility.checkIfNullOrEmpty(user.getSecretCode()))
            throw new BadRequestException(Constants.error_secret_code_null_empty);

        if(userRepository.validateUserByEmailId(user.getEmailId()))
            throw new CustomException(Constants.error_user_invalid_email_id);
    }

    public void validateUpdatePasswordRequest(User user) {
        if(Utility.checkIfNullOrEmpty(user.getEmailId()))
            throw new BadRequestException(Constants.error_email_id_null_or_empty);

        if(Utility.checkIfNullOrEmpty(user.getPassword()))
            throw new BadRequestException(Constants.error_password_null_or_empty);

        if(Utility.checkIfNullOrEmpty(user.getSecretCode()))
            throw new BadRequestException(Constants.error_secret_code_null_empty);

        if(userRepository.validateUserByEmailId(user.getEmailId()))
            throw new CustomException(Constants.error_user_invalid_email_id);
    }

    public void validateSendCodeRequest(String emailId) {
        if(Utility.checkIfNullOrEmpty(emailId))
            throw new BadRequestException(Constants.error_email_id_null_or_empty);

        if(userRepository.validateUserByEmailId(emailId))
            throw new CustomException(Constants.error_user_invalid_email_id);
    }

    public void validateCompleteDeleteRequest(User user) {
        if(Utility.checkIfNullOrEmpty(user.getEmailId()))
            throw new BadRequestException(Constants.error_email_id_null_or_empty);

        if(Utility.checkIfNullOrEmpty(user.getSecretCode()))
            throw new BadRequestException(Constants.error_secret_code_null_empty);

        if(userRepository.validateUserByEmailId(user.getEmailId()))
            throw new CustomException(Constants.error_user_invalid_email_id);
    }

    public void validateAdminCompleteDeleteRequest(String emailId) {
        if(Utility.checkIfNullOrEmpty(emailId))
            throw new BadRequestException(Constants.error_email_id_null_or_empty);

        if(userRepository.validateUserByEmailId(emailId))
            throw new CustomException(Constants.error_user_invalid_email_id);
    }

}
