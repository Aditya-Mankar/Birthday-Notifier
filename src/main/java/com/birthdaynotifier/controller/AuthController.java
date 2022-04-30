package com.birthdaynotifier.controller;

import com.birthdaynotifier.constant.Constants;
import com.birthdaynotifier.constant.LoggingConstants;
import com.birthdaynotifier.constant.RequestPathConstants;
import com.birthdaynotifier.model.CustomUser;
import com.birthdaynotifier.model.User;
import com.birthdaynotifier.service.CustomUserDetailsService;
import com.birthdaynotifier.service.UserService;
import com.birthdaynotifier.utility.JWTUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final JWTUtility jwtUtility;
    private final CustomUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthController(JWTUtility jwtUtility, CustomUserDetailsService userDetailsService, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtUtility = jwtUtility;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping(RequestPathConstants.authenticate)
    public ResponseEntity<String> authenticate(@RequestBody User user) {
        logger.info(LoggingConstants.message_logging_user + user.getUsername());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()
                    )
            );

            CustomUser userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            String token = jwtUtility.generateToken(userDetails);
            userService.updateUserLastLoggedIn(user.getUsername());

            logger.info(LoggingConstants.success_logging_user + user.getUsername());
            return ResponseEntity.ok().body(token);
        } catch (BadCredentialsException e) {
            logger.error(LoggingConstants.fail_logging_user + user.getUsername() + Constants.error + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logger.error(LoggingConstants.fail_logging_user + user.getUsername() + Constants.error + e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
