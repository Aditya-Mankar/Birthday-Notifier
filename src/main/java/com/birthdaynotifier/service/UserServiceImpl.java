package com.birthdaynotifier.service;

import com.birthdaynotifier.constant.Constant;
import com.birthdaynotifier.exception.BadRequestException;
import com.birthdaynotifier.exception.CustomException;
import com.birthdaynotifier.model.User;
import com.birthdaynotifier.respository.IUserRepository;
import com.birthdaynotifier.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRequestValidator requestValidator;

    @Override
    public ResponseEntity<?> getUserByUsername(String username) {
        try {
            if(Utility.checkIfNullOrEmpty(username))
                throw new BadRequestException(Constant.error_username_null_or_empty);

            if(!userRepository.checkIfUserExistsByUsername(username))
                throw new CustomException(Constant.error_no_user_exists_with_email_id);

            User user = userRepository.getUserByUsername(username);

            return ResponseEntity.ok().headers(new HttpHeaders()).body(user);
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        } catch (CustomException e) {
            return ResponseEntity.internalServerError().body(e.getErrorMessage());
        }
    }

    @Override
    public ResponseEntity<?> createNewUser(User user) {
        try {
            requestValidator.validateAddUserRequest(user);

            user.setId(Utility.generateID(3));
            user.setIsEmailIdVerified(Constant.value_false);
            user.setSecretCode(Utility.generateCode(7));
            user.setCreatedAt(Instant.now().toString());

            if(userRepository.checkIfUserExistsByEmailId(user.getEmailId()))
                throw new CustomException(Constant.error_user_already_exists_email_id);

            if(userRepository.checkIfUserExistsByUsername(user.getUsername()))
                throw new CustomException(Constant.error_user_already_exists_username);

            userRepository.createNewUser(user);

            return ResponseEntity.ok().headers(new HttpHeaders()).body(Constant.success_user_added);
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        } catch (CustomException e) {
            return ResponseEntity.internalServerError().body(e.getErrorMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateUser(User user) {
        try {
            if(Utility.checkIfNullOrEmpty(user.getId()))
                throw new BadRequestException(Constant.error_id_null_or_empty);

            if(!userRepository.checkIfUserExistsById(user.getId()))
                throw new CustomException(Constant.error_no_user_exists_with_id);

            user.setUpdatedAt(Instant.now().toString());

            userRepository.updateUser(user);

            return ResponseEntity.ok().headers(new HttpHeaders()).body(Constant.success_user_updated);
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        } catch (CustomException e) {
            return ResponseEntity.internalServerError().body(e.getErrorMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteUser(int id) {
        try {
            if(Utility.checkIfNullOrEmpty(id))
                throw new BadRequestException(Constant.error_id_null_or_empty);

            if(!userRepository.checkIfUserExistsById(String.valueOf(id)))
                throw new CustomException(Constant.error_no_user_exists_with_id);

            userRepository.deleteUser(id);

            return ResponseEntity.ok().headers(new HttpHeaders()).body(Constant.success_user_deleted);
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        } catch (CustomException e) {
            return ResponseEntity.internalServerError().body(e.getErrorMessage());
        }
    }

}
