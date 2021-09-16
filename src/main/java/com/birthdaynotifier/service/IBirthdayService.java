package com.birthdaynotifier.service;

import com.birthdaynotifier.exception.BadRequestException;
import com.birthdaynotifier.model.Birthday;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IBirthdayService {

    public List<Birthday> getAllBirthdays();

    public ResponseEntity<?> getBirthdaysByEmailId(String emailId);

    public ResponseEntity<?> addNewBirthday(Birthday birthday) throws BadRequestException;

    public ResponseEntity<?> updateBirthday(Birthday birthday);

    public ResponseEntity<?> deleteBirthday(int id);

    public ResponseEntity<?> insertBirthday(Birthday birthday);

    public ResponseEntity<?> modifyBirthday(Birthday birthday);

}
