package com.birthdaynotifier.service;

import com.birthdaynotifier.constant.Constants;
import com.birthdaynotifier.constant.LoggingConstants;
import com.birthdaynotifier.exception.BadRequestException;
import com.birthdaynotifier.exception.CustomException;
import com.birthdaynotifier.model.User;
import com.birthdaynotifier.repository.UserRepository;
import com.birthdaynotifier.utility.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserService {

    private final RequestValidatorService requestValidator;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(RequestValidatorService requestValidator, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.requestValidator = requestValidator;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public ResponseEntity<Object> getUserByUsername(String username) {
        logger.info(LoggingConstants.message_get_user + username);
        try {
            requestValidator.validateFetchUserRequest(username);

            User user = userRepository.getUserByUsername(username);

            User userResponse = User.builder()
                    .setId(user.getId())
                    .setEmailId(user.getEmailId())
                    .setUsername(user.getUsername())
                    .setIsEmailIdVerified(user.getIsEmailIdVerified());

            logger.info(LoggingConstants.success_get_user + username);
            return ResponseEntity.ok().body(userResponse);
        } catch (BadRequestException e) {
            logger.error(LoggingConstants.fail_get_user + username + Constants.error + e.getErrorMessage());
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        } catch (CustomException e) {
            logger.error(LoggingConstants.fail_get_user + username + Constants.error + e.getErrorMessage());
            return ResponseEntity.internalServerError().body(e.getErrorMessage());
        } catch (Exception e) {
            logger.error(LoggingConstants.fail_get_user + username + Constants.error + e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    public ResponseEntity<String> createUser(User user) {
        logger.info(LoggingConstants.message_insert_user + user.getEmailId());
        try {
            requestValidator.validateInsertUserRequest(user);

            user.setId(Utility.generateUUID(3));
            user.setIsEmailIdVerified(Constants.value_false);
            user.setPassword(encodePassword(user.getPassword()));
            user.setSecretCode(Utility.generateCode(7));
            user.setCreatedAt(Instant.now().toString());
            user.setRole(Constants.role_user);

            userRepository.createUser(user);

            logger.info(LoggingConstants.success_insert_user + user.getEmailId());
            return ResponseEntity.ok().body(LoggingConstants.success_insert_user + user.getEmailId());
        } catch (BadRequestException e) {
            logger.error(LoggingConstants.fail_insert_user + user.getEmailId() + Constants.error + e.getErrorMessage());
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        } catch (CustomException e) {
            logger.error(LoggingConstants.fail_insert_user + user.getEmailId() + Constants.error + e.getErrorMessage());
            return ResponseEntity.internalServerError().body(e.getErrorMessage());
        } catch (Error e) {
            logger.error(LoggingConstants.fail_insert_user + user.getEmailId() + Constants.error + e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    public ResponseEntity<String> verifyEmailId(User user) {
        logger.info(LoggingConstants.message_verify_user + user.getEmailId());
        try {
            requestValidator.validateUserEmailIdRequest(user);

            verifySecretCode(user);

            user.setUpdatedAt(Instant.now().toString());
            user.setIsEmailIdVerified(Constants.value_true);

            userRepository.verifyEmailId(user);

            logger.info(LoggingConstants.success_verify_user + user.getEmailId());
            return ResponseEntity.ok().body(LoggingConstants.success_verify_user + user.getEmailId());
        } catch (BadRequestException e) {
            logger.error(LoggingConstants.fail_verify_user + user.getEmailId() + Constants.error + e.getErrorMessage());
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        } catch (CustomException e) {
            logger.error(LoggingConstants.fail_verify_user + user.getEmailId() + Constants.error + e.getErrorMessage());
            return ResponseEntity.internalServerError().body(e.getErrorMessage());
        } catch (Error e) {
            logger.error(LoggingConstants.fail_verify_user + user.getEmailId() + Constants.error + e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    public ResponseEntity<String> updatePassword(User user) {
        logger.info(LoggingConstants.message_update_password + user.getEmailId());
        try {
            requestValidator.validateUpdatePasswordRequest(user);

            verifySecretCode(user);

            user.setUpdatedAt(Instant.now().toString());
            user.setPassword(encodePassword(user.getPassword()));

            userRepository.updatePassword(user);

            logger.info(LoggingConstants.success_update_password + user.getEmailId());
            return ResponseEntity.ok().body(LoggingConstants.success_update_password + user.getEmailId());
        } catch (BadRequestException e) {
            logger.error(LoggingConstants.fail_update_password + user.getEmailId() + Constants.error + e.getErrorMessage());
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        } catch (CustomException e) {
            logger.error(LoggingConstants.fail_update_password + user.getEmailId() + Constants.error + e.getErrorMessage());
            return ResponseEntity.internalServerError().body(e.getErrorMessage());
        } catch (Error e) {
            logger.error(LoggingConstants.fail_update_password + user.getEmailId() + Constants.error + e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    public String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    private void verifySecretCode(User user) {
        String code = userRepository.fetchCode(user.getEmailId());

        if (!code.equals(user.getSecretCode()))
            throw new CustomException(Constants.error_user_invalid_code);
    }

}
