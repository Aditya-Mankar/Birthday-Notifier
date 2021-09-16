package com.birthdaynotifier.controller;

import com.birthdaynotifier.constant.Constant;
import com.birthdaynotifier.exception.BadRequestException;
import com.birthdaynotifier.model.Birthday;
import com.birthdaynotifier.service.IBirthdayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constant.request_path_birthday)
public class BirthdayController {

    @Autowired
    private IBirthdayService birthdayService;

    @GetMapping(Constant.request_path_birthday_get_all)
    public List<Birthday> getAllBirthdays() {
        return birthdayService.getAllBirthdays();
    }

    @GetMapping(Constant.request_path_birthday_get_email_id)
    public ResponseEntity<?> getBirthdaysByEmailId(@PathVariable String emailId) {
        return birthdayService.getBirthdaysByEmailId(emailId);
    }

    @PostMapping(Constant.request_path_birthday_add)
    public ResponseEntity<?> addNewBirthday(@RequestBody Birthday birthday) throws BadRequestException {
        return birthdayService.addNewBirthday(birthday);
    }

    @PutMapping(Constant.request_path_birthday_update)
    public ResponseEntity<?> updateBirthday(@RequestBody Birthday birthday) {
        return birthdayService.updateBirthday(birthday);
    }

    @DeleteMapping(Constant.request_path_birthday_delete_email_id)
    public ResponseEntity<?> deleteBirthday(@PathVariable int id) {
        return birthdayService.deleteBirthday(id);
    }

    @PostMapping(Constant.request_path_birthday_insert)
    public ResponseEntity<?> insertBirthday(@RequestBody Birthday birthday) {
        return birthdayService.insertBirthday(birthday);
    }

    @PutMapping(Constant.request_path_birthday_modify)
    public ResponseEntity<?> modifyBirthday(@RequestBody Birthday birthday) {
        return birthdayService.modifyBirthday(birthday);
    }

}
