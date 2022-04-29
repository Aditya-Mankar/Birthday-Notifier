package com.birthdaynotifier.controller;

import com.birthdaynotifier.constant.RequestPathConstants;
import com.birthdaynotifier.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RequestPathConstants.admin_controller)
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(RequestPathConstants.read_all_users)
    public ResponseEntity<Object> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping(RequestPathConstants.admin_user_complete_delete)
    public ResponseEntity<String> completeDelete(@PathVariable String emailId) {
        return userService.adminCompleteDelete(emailId);
    }

}
