package com.birthdaynotifier.repository;

import com.birthdaynotifier.mapper.BirthdayRowMapper;
import com.birthdaynotifier.model.Birthday;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {BirthdayRepository.class})
@ExtendWith(SpringExtension.class)
public class BirthdayRepositoryTest {

    @Autowired
    BirthdayRepository birthdayRepository;
    @MockBean
    NamedParameterJdbcTemplate jdbcTemplate;

    private Birthday birthday;

    @PostConstruct
    void createBirthday() {
        birthday = Birthday.builder()
                .setName("testName")
                .setId("123")
                .setEmailId("test@gmail.com")
                .build();
    }

    @Test
    void testGetBirthdaysByEmailId() {
        try {
            when(jdbcTemplate.query(anyString(), anyMap(), any(BirthdayRowMapper.class))).thenReturn(List.of(birthday));

            List<Birthday> response = birthdayRepository.getBirthdaysByEmailId(birthday.getEmailId());

            assertEquals(response.get(0).getId(), birthday.getId());
            assertEquals(response.get(0).getName(), birthday.getName());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception unexpected");
        }
    }

    @Test
    void testGetBirthdaysByEmailId1() {
        try {
            when(jdbcTemplate.query(anyString(), anyMap(), any(BirthdayRowMapper.class))).thenThrow(Exception.class);

            List<Birthday> response = birthdayRepository.getBirthdaysByEmailId(birthday.getEmailId());

            fail("Exception expected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAddNewBirthday() {
        try {
            when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

            birthdayRepository.addNewBirthday(birthday);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception unexpected");
        }
    }

    @Test
    void testAddNewBirthday1() {
        try {
            when(jdbcTemplate.update(anyString(), anyMap())).thenThrow(Exception.class);

            birthdayRepository.addNewBirthday(birthday);

            fail("Exception expected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCheckBirthdayExists() {
        try {
            when(jdbcTemplate.queryForObject(anyString(), anyMap(), (Class<Object>) any())).thenReturn(1);

            Boolean response = birthdayRepository.checkBirthdayExists(Integer.parseInt(birthday.getId()));

            assertTrue(response);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception unexpected");
        }
    }

    @Test
    void testCheckBirthdayExists1() {
        try {
            when(jdbcTemplate.queryForObject(anyString(), anyMap(), (Class<Object>) any())).thenThrow(Exception.class);

            Boolean response = birthdayRepository.checkBirthdayExists(Integer.parseInt(birthday.getId()));

            fail("Exception expected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdateBirthday() {
        try {
            when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

            birthdayRepository.updateBirthday(birthday);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception unexpected");
        }
    }

    @Test
    void testUpdateBirthday1() {
        try {
            when(jdbcTemplate.update(anyString(), anyMap())).thenThrow(Exception.class);

            birthdayRepository.updateBirthday(birthday);

            fail("Exception expected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDeleteBirthday() {
        try {
            when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

            birthdayRepository.deleteBirthday(Integer.parseInt(birthday.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception unexpected");
        }
    }

    @Test
    void testDeleteBirthday1() {
        try {
            when(jdbcTemplate.update(anyString(), anyMap())).thenThrow(Exception.class);

            birthdayRepository.deleteBirthday(Integer.parseInt(birthday.getId()));

            fail("Exception expected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}