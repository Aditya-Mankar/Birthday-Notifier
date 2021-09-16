package com.birthdaynotifier.controller;

import com.birthdaynotifier.constant.Constant;
import com.birthdaynotifier.model.User;
import com.birthdaynotifier.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constant.request_path_user)
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping(Constant.request_path_user_get_username)
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping(Constant.request_path_user_add)
    public ResponseEntity<?> createNewUser(@RequestBody User user) {
        return userService.createNewUser(user);
    }

    @PutMapping(Constant.request_path_user_update)
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping(Constant.request_path_user_delete_id)
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateUser(@RequestBody User user) {
        return userService.validateUser(user);
    }

}
