package com.birthdaynotifier.respository;

import com.birthdaynotifier.model.Birthday;

import java.util.List;

public interface IBirthdayRepository {

    public List<Birthday> getAllBirthdays();

    public void validateEmailId(String emailId);

    public List<Birthday> getBirthdaysByEmailId(String emailId);

    public void addNewBirthday(Birthday birthday);

    public void updateBirthday(Birthday birthday);

    public void checkBirthdayExists(int id);

    public void deleteBirthday(int id);

    public List<Birthday> getBirthdaysByDateAndMonth(Birthday request);

}
