package com.birthdaynotifier.controller;

import com.birthdaynotifier.model.User;
import com.birthdaynotifier.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/get/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/add")
    public ResponseEntity<?> createNewUser(@RequestBody User user) {
        return userService.createNewUser(user);
    }

}
