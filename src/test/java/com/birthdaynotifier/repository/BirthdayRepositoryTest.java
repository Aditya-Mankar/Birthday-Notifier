package com.birthdaynotifier.repository;

import com.birthdaynotifier.model.Birthday;
import com.birthdaynotifier.utility.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BirthdayRepositoryTest {

    @Autowired
    private BirthdayRepository birthdayRepository;

    private String id;
    private Birthday birthday;

    @BeforeEach
    public void setup() {
        id = Utility.generateUUID(3);

        birthday = Birthday.builder()
                .setId(id)
                .setEmailId("birthday.notifier.service@gmail.com")
                .setName("test")
                .setBirthDate(1)
                .setBirthMonth(1)
                .setRemindBeforeDays(1)
                .setRemindDate(1)
                .setRemindMonth(1)
                .setCreatedAt("")
                .setUpdatedAt("")
                .build();
    }

    @Test
    @DisplayName("Test should check if birthdays are fetched using email id")
    void shouldGetBirthdaysByEmailId() {
        birthdayRepository.addNewBirthday(birthday);

        List<Birthday> birthdayList = birthdayRepository.getBirthdaysByEmailId(birthday.getEmailId());

        assertThat(birthdayList.size() > 0).isTrue();
    }

    @Test
    @DisplayName("Test should check if birthday is added")
    void shouldAddNewBirthday() {
        birthdayRepository.addNewBirthday(birthday);

        assertThat(birthdayRepository.checkBirthdayExists(Integer.parseInt(id))).isTrue();
    }

    @Test
    @DisplayName("Test should check if birthday exists")
    void shouldCheckBirthdayExists() {
        birthdayRepository.addNewBirthday(birthday);

        assertThat(birthdayRepository.checkBirthdayExists(Integer.parseInt(id))).isTrue();
    }

    @Test
    @DisplayName("Test should check if birthday is updated")
    void shouldUpdateBirthday() {
        birthdayRepository.addNewBirthday(birthday);

        String name = "updated name";

        birthday.setName(name);
        birthdayRepository.updateBirthday(birthday);

        List<Birthday> birthdayList = birthdayRepository.getBirthdaysByEmailId(birthday.getEmailId());

        Birthday updatedBirthday = birthdayList.stream().filter(b -> b.getId().equals(id)).findAny().get();

        assertThat(updatedBirthday.getName().equals(name)).isTrue();
    }

    @Test
    @DisplayName("Test should check if birthday is deleted")
    void shouldDeleteBirthday() {
        birthdayRepository.deleteBirthday(Integer.parseInt(id));

        assertThat(birthdayRepository.checkBirthdayExists(Integer.parseInt(id))).isFalse();
    }

    @Test
    @DisplayName("Test should check if birthdays are fetched using birth date and birth month id")
    void shouldGetBirthdaysByDateAndMonth() {
        birthdayRepository.addNewBirthday(birthday);

        List<Birthday> birthdayList = birthdayRepository.getBirthdaysByDateAndMonth(birthday);

        assertThat(birthdayList.size() > 0).isTrue();
    }

    @Test
    @DisplayName("Test should check if birthdays are deleted using email id")
    void shouldDeleteBirthdaysByEmailId() {
        birthdayRepository.deleteBirthdaysByEmailId(birthday.getEmailId());

        List<Birthday> birthdayList = birthdayRepository.getBirthdaysByEmailId(birthday.getEmailId());

        assertThat(birthdayList.size() > 0).isFalse();
    }

}