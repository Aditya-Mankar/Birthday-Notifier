package com.birthdaynotifier.controller;

import com.birthdaynotifier.exception.BadRequestException;
import com.birthdaynotifier.model.Birthday;
import com.birthdaynotifier.service.IBirthdayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/birthday")
public class BirthdayController {

    @Autowired
    private IBirthdayService birthdayService;

    @GetMapping("/getAll")
    public List<Birthday> getAllBirthdays() {
        return birthdayService.getAllBirthdays();
    }

    @GetMapping("/get/{emailId}")
    public ResponseEntity<?> getBirthdaysByEmailId(@PathVariable String emailId) {
        return birthdayService.getBirthdaysByEmailId(emailId);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewBirthday(@RequestBody Birthday birthday) throws BadRequestException {
        return birthdayService.addNewBirthday(birthday);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateBirthday(@RequestBody Birthday birthday) {
        return birthdayService.updateBirthday(birthday);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBirthday(@PathVariable int id) {
        return birthdayService.deleteBirthday(id);
    }

}
