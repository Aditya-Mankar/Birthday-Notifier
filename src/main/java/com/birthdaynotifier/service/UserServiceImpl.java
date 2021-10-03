package com.birthdaynotifier.service;

import com.birthdaynotifier.constant.Constant;
import com.birthdaynotifier.exception.BadRequestException;
import com.birthdaynotifier.exception.CustomException;
import com.birthdaynotifier.model.User;
import com.birthdaynotifier.respository.IUserRepository;
import com.birthdaynotifier.utility.JWTUtility;
import com.birthdaynotifier.utility.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRequestValidator requestValidator;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JWTUtility jwtUtility;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public ResponseEntity<?> getUserByUsername(String username) {
        try {
            if (Utility.checkIfNullOrEmpty(username))
                throw new BadRequestException(Constant.error_username_null_or_empty);

            if (!userRepository.checkIfUserExistsByUsername(username))
                throw new CustomException(Constant.error_no_user_exists_with_email_id);

            User user = userRepository.getUserByUsername(username);

            User userResponse = new User(user.getId(), user.getEmailId(), user.getUsername(), user.getIsEmailIdVerified());

            return ResponseEntity.ok().headers(new HttpHeaders()).body(userResponse);
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

            if (userRepository.checkIfUserExistsByEmailId(user.getEmailId()))
                throw new CustomException(Constant.error_user_already_exists_email_id);

            if (userRepository.checkIfUserExistsByUsername(user.getUsername()))
                throw new CustomException(Constant.error_user_already_exists_username);

            user.setId(Utility.generateID(3));
            user.setIsEmailIdVerified(Constant.value_false);
            user.setPassword(encodePassword(user.getPassword()));
            user.setSecretCode(Utility.generateCode(7));
            user.setCreatedAt(Instant.now().toString());

            userRepository.createNewUser(user);

            logger.info("User successfully created with email id: " + user.getEmailId());

            return ResponseEntity.ok().headers(new HttpHeaders()).body(Constant.success_user_added);
        } catch (BadRequestException e) {
            logger.error("Invalid request for creating user with email id: " + user.getEmailId() + " " + e.getErrorMessage());
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        } catch (CustomException e) {
            logger.error("Error occurred during creating user with email id: " + user.getEmailId() + " " + e.getErrorMessage());
            return ResponseEntity.internalServerError().body(e.getErrorMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateUser(User user) {
        try {
            if (Utility.checkIfNullOrEmpty(user.getId()))
                throw new BadRequestException(Constant.error_id_null_or_empty);

            if (!userRepository.checkIfUserExistsById(user.getId()))
                throw new CustomException(Constant.error_no_user_exists_with_id);

            user.setUpdatedAt(Instant.now().toString());

            userRepository.updateUser(user);

            logger.info("User updated successfully with email id: " + user.getEmailId());

            return ResponseEntity.ok().headers(new HttpHeaders()).body(Constant.success_user_updated);
        } catch (BadRequestException e) {
            logger.error("Invalid request for updating user with email id: " + user.getEmailId() + " " + e.getErrorMessage());
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        } catch (CustomException e) {
            logger.error("Error occurred during updating user with email id: " + user.getEmailId() + " " + e.getErrorMessage());
            return ResponseEntity.internalServerError().body(e.getErrorMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteUser(int id) {
        try {
            if (Utility.checkIfNullOrEmpty(id))
                throw new BadRequestException(Constant.error_id_null_or_empty);

            if (!userRepository.checkIfUserExistsById(String.valueOf(id)))
                throw new CustomException(Constant.error_no_user_exists_with_id);

            userRepository.deleteUser(id);

            return ResponseEntity.ok().headers(new HttpHeaders()).body(Constant.success_user_deleted);
        } catch (BadRequestException e) {
            logger.error("Invalid request for deleting user " + e.getErrorMessage());
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        } catch (CustomException e) {
            logger.error("Error occurred during deleting user with id: " + id + " " + e.getErrorMessage());
            return ResponseEntity.internalServerError().body(e.getErrorMessage());
        }
    }

    @Override
    public ResponseEntity<?> validateUser(User user) {

        try {
            String encryptedPassword = userRepository.fetchEncryptedPassword(user.getUsername());

            if (!encodePassword(user.getPassword()).equals(encryptedPassword))
                throw new CustomException(Constant.error_user_invalid_password);

            return ResponseEntity.ok().headers(new HttpHeaders()).body(Constant.success_user_authenticated);
        } catch (CustomException e) {
            return ResponseEntity.internalServerError().body(e.getErrorMessage());
        }
    }

    @Override
    public ResponseEntity<?> verifyEmailId(User user) {
        try {
            requestValidator.validateUserEmailIdRequest(user);

            if (!userRepository.checkIfUserExistsByEmailId(user.getEmailId()))
                throw new CustomException(Constant.error_no_user_exists_with_email_id);

            String code = userRepository.fetchCode(user.getEmailId());

            if (!code.equals(user.getSecretCode()))
                throw new CustomException(Constant.error_invalid_code);

            user.setUpdatedAt(Instant.now().toString());
            user.setIsEmailIdVerified("true");

            userRepository.updateUserEmailId(user);

            logger.info("User with email id: " + user.getEmailId() + " verified successfully");

            return ResponseEntity.ok().headers(new HttpHeaders()).body(Constant.success_user_email_id_verified);
        } catch (BadRequestException e) {
            logger.error("Invalid request for verification of email id: " + user.getEmailId() + " " + e.getErrorMessage());
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        } catch (CustomException e) {
            logger.error("Error occurred during verification of email id: " + user.getEmailId() + " " + e.getErrorMessage());
            return ResponseEntity.internalServerError().body(e.getErrorMessage());
        }
    }

    @Override
    public ResponseEntity<?> updatePassword(User user) {
        try {
            requestValidator.validateUserUpdatePasswordRequest(user);

            if (!userRepository.checkIfUserExistsByEmailId(user.getEmailId()))
                throw new CustomException(Constant.error_no_user_exists_with_email_id);

            String code = userRepository.fetchCode(user.getEmailId());

            if (!code.equals(user.getSecretCode()))
                throw new CustomException(Constant.error_invalid_code);

            user.setUpdatedAt(Instant.now().toString());
            user.setPassword(encodePassword(user.getPassword()));

            userRepository.updateUserPassword(user);

            logger.info("Password update for user with email id " + user.getEmailId() + " successful");

            return ResponseEntity.ok().headers(new HttpHeaders()).body(Constant.success_user_update_password);
        } catch (BadRequestException e) {
            logger.error("Invalid request for updating password " + e.getErrorMessage());
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        } catch (CustomException e) {
            logger.error("Error occurred while updating password " + e.getErrorMessage());
            return ResponseEntity.internalServerError().body(e.getErrorMessage());
        }
    }

    @Override
    public ResponseEntity<?> login(User user) {
        try {
            requestValidator.validateLoginRequest(user);

            if (!userRepository.checkIfUserExistsByUsername(user.getUsername()))
                throw new CustomException(Constant.error_user_no_record_with_username);

            validateUser(user);

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()
                    )
            );

            final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

            final String token = jwtUtility.generateToken(userDetails);

            logger.info("User with username: " + user.getUsername() + " logged in");

            return ResponseEntity.ok().body(token);
        } catch (BadRequestException e) {
            logger.error("Invalid request for login for user with username: " + user.getUsername());
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        } catch (CustomException e) {
            logger.error("Error for login for user with username: " + user.getUsername() + " " + e.getErrorMessage());
            return ResponseEntity.internalServerError().body(e.getErrorMessage());
        } catch (BadCredentialsException e) {
            logger.error("Invalid credentials for login for user with username: " + user.getUsername());
            return ResponseEntity.internalServerError().body(Constant.error_user_invalid_credentials);
        } catch (Exception e) {
            logger.error("Error for login for user with username: " + user.getUsername() + " " + e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    public String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

}
