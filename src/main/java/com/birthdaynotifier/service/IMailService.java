package com.birthdaynotifier.service;

import com.birthdaynotifier.model.Birthday;
import com.birthdaynotifier.model.Mail;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;

public interface IMailService {

    public void sendHtmlMail(Mail mail, String template) throws MessagingException;

    public String sendMail(Birthday birthday) throws MessagingException;

    public ResponseEntity<?> sendCodeForUser(String emailId);

}
