package com.birthdaynotifier.controller;

import com.birthdaynotifier.constant.RequestPathConstants;
import com.birthdaynotifier.service.MailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RequestPathConstants.mail_controller)
public class MailController {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping(RequestPathConstants.send_code)
    public ResponseEntity<String> sendCodeForUser(@PathVariable String emailId) {
        return mailService.sendCodeForUser(emailId);
    }

}
