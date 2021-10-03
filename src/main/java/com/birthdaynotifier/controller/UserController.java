package com.birthdaynotifier.controller;

import com.birthdaynotifier.constant.Constant;
import com.birthdaynotifier.model.User;
import com.birthdaynotifier.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constant.request_path_user)
public class UserController {

    @Autowired
    private IUserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(Constant.request_path_user_get_username)
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping(Constant.request_path_user_add)
    public ResponseEntity<?> createNewUser(@RequestBody User user) {
        logger.info("Creating new user with email id: " + user.getEmailId());
        return userService.createNewUser(user);
    }

    @PutMapping(Constant.request_path_user_update)
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        logger.info("Updating user with email id: " + user.getEmailId());
        return userService.updateUser(user);
    }

    @DeleteMapping(Constant.request_path_user_delete_id)
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        logger.info("Deleting user with id: " + id);
        return userService.deleteUser(id);
    }

    @PostMapping(Constant.request_path_user_verify_email_id)
    public ResponseEntity<?> verifyEmailId(@RequestBody User user) {
        logger.info("Request for verifying email id: " + user.getEmailId());
        return userService.verifyEmailId(user);
    }

    @PostMapping(Constant.request_path_user_update_password)
    public ResponseEntity<?> updatePassword(@RequestBody User user) {
        logger.info("Request for updating password for user with email id: " + user.getEmailId());
        return userService.updatePassword(user);
    }

    @PostMapping(Constant.request_path_user_login)
    public ResponseEntity<?> login(@RequestBody User user) {
        logger.info("Login request from user with username: " + user.getUsername());
        return userService.login(user);
    }

}
