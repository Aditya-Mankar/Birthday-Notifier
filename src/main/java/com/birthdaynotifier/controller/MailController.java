package com.birthdaynotifier.controller;

import com.birthdaynotifier.constant.Constant;
import com.birthdaynotifier.model.Birthday;
import com.birthdaynotifier.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping(Constant.request_path_mail)
public class MailController {

    @Autowired
    private IMailService mailService;

    @GetMapping(Constant.request_path_mail_send_code)
    public ResponseEntity<?> sendCodeForUser(@PathVariable String emailId) {
        return mailService.sendCodeForUser(emailId);
    }

}
