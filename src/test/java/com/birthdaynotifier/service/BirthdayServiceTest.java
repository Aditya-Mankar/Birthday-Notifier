package com.birthdaynotifier.service;

import com.birthdaynotifier.constant.LoggingConstants;
import com.birthdaynotifier.model.Birthday;
import com.birthdaynotifier.repository.BirthdayRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {BirthdayService.class})
@ExtendWith(SpringExtension.class)
public class BirthdayServiceTest {

    @Autowired
    BirthdayService birthdayService;
    @MockBean
    RequestValidatorService requestValidator;
    @MockBean
    BirthdayRepository birthdayRepository;

    private Birthday birthday;

    @PostConstruct
    void createBirthday() {
        birthday = Birthday.builder()
                .setName("testName")
                .setId("test123")
                .build();
    }

    @Test
    void testGetBirthdaysByEmailId() {
        try {
            String emailId = "test@gmail.com";

            when(birthdayRepository.getBirthdaysByEmailId(anyString())).thenReturn(List.of(birthday));

            ResponseEntity<Object> response = birthdayService.getBirthdaysByEmailId(emailId);

            assertTrue(response.getBody() instanceof List);

            List<Birthday> responseBody = (List<Birthday>) response.getBody();

            assertEquals(responseBody.get(0).getName(), birthday.getName());
            assertEquals(responseBody.get(0).getId(), birthday.getId());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception unexpected");
        }
    }

    @Test
    void testGetBirthdaysByEmailId1() {
        try {
            String emailId = "test@gmail.com";

            when(birthdayRepository.getBirthdaysByEmailId(anyString())).thenThrow(Exception.class);

            ResponseEntity<Object> response = birthdayService.getBirthdaysByEmailId(emailId);

            fail("Exception expected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInsertBirthday() {
        try {
            doNothing().when(birthdayRepository).addNewBirthday(any());

            ResponseEntity<String> response = birthdayService.insertBirthday(birthday);

            assertEquals(response.getBody(), LoggingConstants.success_insert_birthday + birthday.getEmailId());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception unexpected");
        }
    }

    @Test
    void testInsertBirthday1() {
        try {
            doThrow(new Exception()).when(birthdayRepository).addNewBirthday(any());

            ResponseEntity<String> response = birthdayService.insertBirthday(birthday);

            fail("Exception expected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testModifyBirthday() {
        try {
            doNothing().when(birthdayRepository).updateBirthday(any());

            ResponseEntity<String> response = birthdayService.modifyBirthday(birthday);

            assertEquals(response.getBody(), LoggingConstants.success_update_birthday + birthday.getEmailId());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception unexpected");
        }
    }

    @Test
    void testModifyBirthday1() {
        try {
            doThrow(new Exception()).when(birthdayRepository).updateBirthday(any());

            ResponseEntity<String> response = birthdayService.modifyBirthday(birthday);

            fail("Exception expected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDeleteBirthday() {
        try {
            int id = 123;

            doNothing().when(birthdayRepository).deleteBirthday(anyInt());

            ResponseEntity<String> response = birthdayService.deleteBirthday(id);

            assertEquals(response.getBody(), LoggingConstants.success_delete_birthday + id);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception unexpected");
        }
    }

    @Test
    void testDeleteBirthday1() {
        try {
            int id = 123;

            doThrow(new Exception()).when(birthdayRepository).deleteBirthday(anyInt());

            ResponseEntity<String> response = birthdayService.deleteBirthday(id);

            fail("Exception expected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetBirthdaysByDateAndMonth() {
        try {
            when(birthdayRepository.getBirthdaysByDateAndMonth(any())).thenReturn(List.of(birthday));

            List<Birthday> response = birthdayService.getBirthdaysByDateAndMonth(birthday);

            assertEquals(response.get(0).getId(), birthday.getId());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception unexpected");
        }
    }

    @Test
    void testGetBirthdaysByDateAndMonth1() {
        try {
            doThrow(new Exception()).when(birthdayRepository).getBirthdaysByDateAndMonth(any());

            List<Birthday> response = birthdayService.getBirthdaysByDateAndMonth(birthday);

            fail("Exception expected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
