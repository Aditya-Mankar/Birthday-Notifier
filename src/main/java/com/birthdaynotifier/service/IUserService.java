package com.birthdaynotifier.service;

import com.birthdaynotifier.model.User;
import org.springframework.http.ResponseEntity;

public interface IUserService {

    public ResponseEntity<?> getUserByUsername(String username);

    public ResponseEntity<?> createNewUser(User user);

}
