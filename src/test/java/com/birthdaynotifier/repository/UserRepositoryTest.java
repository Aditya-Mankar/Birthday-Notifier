package com.birthdaynotifier.repository;

import com.birthdaynotifier.mapper.UserRowMapper;
import com.birthdaynotifier.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.PostConstruct;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {UserRepository.class})
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @MockBean
    NamedParameterJdbcTemplate jdbcTemplate;

    private User user;

    @PostConstruct
    void createBirthday() {
        user = User.builder()
                .setId("userId123")
                .setEmailId("test@gmail.com")
                .setUsername("user123")
                .setSecretCode("secretCode")
                .build();
    }

    @Test
    void testValidateUserByEmailId() {
        try {
            when(jdbcTemplate.queryForObject(anyString(), anyMap(), (Class<Object>) any())).thenReturn(1);

            Boolean response = userRepository.validateUserByEmailId(user.getEmailId());

            assertFalse(response);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception unexpected");
        }
    }

    @Test
    void testValidateUserByEmailId1() {
        try {
            when(jdbcTemplate.queryForObject(anyString(), anyMap(), (Class<Object>) any())).thenThrow(Exception.class);

            Boolean response = userRepository.validateUserByEmailId(user.getEmailId());

            fail("Exception expected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testValidateUserByUsername() {
        try {
            when(jdbcTemplate.queryForObject(anyString(), anyMap(), (Class<Object>) any())).thenReturn(1);

            Boolean response = userRepository.validateUserByUsername(user.getUsername());

            assertFalse(response);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception unexpected");
        }
    }

    @Test
    void testValidateUserByUsername1() {
        try {
            when(jdbcTemplate.queryForObject(anyString(), anyMap(), (Class<Object>) any())).thenThrow(Exception.class);

            Boolean response = userRepository.validateUserByUsername(user.getUsername());

            fail("Exception expected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetUserByUsername() {
        try {
            when(jdbcTemplate.queryForObject(anyString(), anyMap(), any(UserRowMapper.class))).thenReturn(user);

            User userResponse = userRepository.getUserByUsername(user.getUsername());

            assertEquals(userResponse.getId(), user.getId());
            assertEquals(userResponse.getEmailId(), user.getEmailId());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception unexpected");
        }
    }

    @Test
    void testGetUserByUsername1() {
        try {
            when(jdbcTemplate.queryForObject(anyString(), anyMap(), (Class<Object>) any())).thenThrow(Exception.class);

            User userResponse = userRepository.getUserByUsername(user.getUsername());

            fail("Exception expected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCreateUser() {
        try {
            when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

            userRepository.createUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception unexpected");
        }
    }

    @Test
    void testCreateUser1() {
        try {
            when(jdbcTemplate.update(anyString(), anyMap())).thenThrow(Exception.class);

            userRepository.createUser(user);

            fail("Exception expected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testFetchCode() {
        try {
            when(jdbcTemplate.queryForObject(anyString(), anyMap(), (Class<Object>) any())).thenReturn(user.getSecretCode());

            String response = userRepository.fetchCode(user.getEmailId());

            assertEquals(response, user.getSecretCode());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception unexpected");
        }
    }

    @Test
    void testFetchCode1() {
        try {
            when(jdbcTemplate.queryForObject(anyString(), anyMap(), (Class<Object>) any())).thenThrow(Exception.class);

            String response = userRepository.fetchCode(user.getEmailId());

            fail("Exception expected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}