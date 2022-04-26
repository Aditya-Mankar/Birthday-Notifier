package com.birthdaynotifier.controller;

import com.birthdaynotifier.constant.RequestPathConstants;
import com.birthdaynotifier.model.Birthday;
import com.birthdaynotifier.service.BirthdayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RequestPathConstants.birthday_controller)
public class BirthdayController {

    private BirthdayService birthdayService;

    public BirthdayController(BirthdayService birthdayService) {
        this.birthdayService = birthdayService;
    }

    @GetMapping(RequestPathConstants.read_by_email_id)
    public ResponseEntity<Object> getBirthdaysByEmailId(@PathVariable String emailId) {
        return birthdayService.getBirthdaysByEmailId(emailId);
    }

    @PostMapping(RequestPathConstants.insert)
    public ResponseEntity<String> insertBirthday(@RequestBody Birthday birthday) {
        return birthdayService.insertBirthday(birthday);
    }

    @PutMapping(RequestPathConstants.modify)
    public ResponseEntity<String> modifyBirthday(@RequestBody Birthday birthday) {
        return birthdayService.modifyBirthday(birthday);
    }

    @DeleteMapping(RequestPathConstants.delete_by_id)
    public ResponseEntity<String> deleteBirthday(@PathVariable int id) {
        return birthdayService.deleteBirthday(id);
    }

}
