package com.birthdaynotifier.service;

import com.birthdaynotifier.constant.SqlConstant;
import com.birthdaynotifier.exception.BadRequestException;
import com.birthdaynotifier.exception.CustomException;
import com.birthdaynotifier.model.User;
import com.birthdaynotifier.respository.IUserRepository;
import com.birthdaynotifier.utility.Utility;
import jdk.jshell.execution.Util;
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
                throw new BadRequestException("Username cannot be null or empty");

            if(!userRepository.checkIfUserExistsByUsername(username)) {
                throw new CustomException("No User exists with that email id");
            }

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
            user.setIsEmailIdVerified(SqlConstant.value_false);
            user.setSecretCode(Utility.generateCode(7));
            user.setCreatedAt(Instant.now().toString());

            if(userRepository.checkIfUserExistsByEmailId(user.getEmailId())) {
                throw new CustomException("User already exists with that email id");
            }

            userRepository.createNewUser(user);

            return ResponseEntity.ok().headers(new HttpHeaders()).body("User added successfully");
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        } catch (CustomException e) {
            return ResponseEntity.internalServerError().body(e.getErrorMessage());
        }
    }

}
