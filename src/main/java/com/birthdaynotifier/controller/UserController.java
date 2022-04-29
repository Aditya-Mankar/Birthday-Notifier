package com.birthdaynotifier.controller;

import com.birthdaynotifier.constant.RequestPathConstants;
import com.birthdaynotifier.model.User;
import com.birthdaynotifier.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RequestPathConstants.user_controller)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(RequestPathConstants.read_by_username)
    public ResponseEntity<Object> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping(RequestPathConstants.insert_user)
    public ResponseEntity<String> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping(RequestPathConstants.verify_email_id)
    public ResponseEntity<String> verifyEmailId(@RequestBody User user) {
        return userService.verifyEmailId(user);
    }

    @PutMapping(RequestPathConstants.update_password)
    public ResponseEntity<String> updatePassword(@RequestBody User user) {
        return userService.updatePassword(user);
    }

    @DeleteMapping(RequestPathConstants.complete_delete)
    public ResponseEntity<String> completeDelete(@RequestBody User user) {
        return userService.completeDelete(user);
    }

}
