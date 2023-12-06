package com.birthdaynotifier.service;

import com.birthdaynotifier.constant.Constants;
import com.birthdaynotifier.constant.LoggingConstants;
import com.birthdaynotifier.model.User;
import com.birthdaynotifier.repository.BirthdayRepository;
import com.birthdaynotifier.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.PostConstruct;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Autowired
    UserService userService;
    @MockBean
    RequestValidatorService requestValidator;
    @MockBean
    UserRepository userRepository;
    @MockBean
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @MockBean
    BirthdayRepository birthdayRepository;

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
    void testGetUserByUsername() {
        try {
            String username = "user123";

            when(userRepository.getUserByUsername(anyString())).thenReturn(user);

            ResponseEntity<Object> response = userService.getUserByUsername(username);

            assertTrue(response.getBody() instanceof User);

            User responseBody = (User) response.getBody();

            assertEquals(responseBody.getId(), user.getId());
            assertEquals(responseBody.getEmailId(), user.getEmailId());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception unexpected");
        }
    }

    @Test
    void testGetUserByUsername1() {
        try {
            String username = "user123";

            doThrow(Exception.class).when(userRepository).getUserByUsername(anyString());

            ResponseEntity<Object> response = userService.getUserByUsername(username);

            fail("Exception expected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCreateUser() {
        try {
            doNothing().when(userRepository).createUser(any());

            ResponseEntity<String> response = userService.createUser(user);

            assertEquals(response.getBody(), LoggingConstants.success_insert_user + user.getEmailId());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception unexpected");
        }
    }

    @Test
    void testCreateUser1() {
        try {
            doThrow(Exception.class).when(userRepository).createUser(any());

            ResponseEntity<String> response = userService.createUser(user);

            fail("Exception expected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testVerifyEmailId() {
        try {
            when(userRepository.fetchCode(anyString())).thenReturn("secretCode");
            doNothing().when(userRepository).verifyEmailId(any());

            ResponseEntity<String> response = userService.verifyEmailId(user);

            assertEquals(response.getBody(), LoggingConstants.success_verify_user + user.getEmailId());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception unexpected");
        }
    }

    @Test
    void testVerifyEmailId1() {
        try {
            when(userRepository.fetchCode(anyString())).thenReturn("invalidCode");

            ResponseEntity<String> response = userService.verifyEmailId(user);

            assertEquals(response.getBody(), Constants.error_user_invalid_code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdatePassword() {
        try {
            when(userRepository.fetchCode(anyString())).thenReturn("secretCode");
            doNothing().when(userRepository).updatePassword(any());

            ResponseEntity<String> response = userService.updatePassword(user);

            assertEquals(response.getBody(), LoggingConstants.success_update_password + user.getEmailId());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception unexpected");
        }
    }

    @Test
    void testUpdatePassword1() {
        try {
            when(userRepository.fetchCode(anyString())).thenReturn("invalidCode");

            ResponseEntity<String> response = userService.updatePassword(user);

            assertEquals(response.getBody(), Constants.error_user_invalid_code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetAllUsers() {
        try {
            when(userRepository.getAllUsers()).thenReturn(List.of(user));
            when(userRepository.getRecordsCountForUser(anyString())).thenReturn(1);

            ResponseEntity<Object> response = userService.getAllUsers();

            assertTrue(response.getBody() instanceof List);

            List<User> users = (List<User>) response.getBody();

            assertEquals(users.get(0).getEmailId(), user.getEmailId());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception unexpected");
        }
    }

    @Test
    void testGetAllUsers1() {
        try {
            doThrow(Exception.class).when(userRepository).getAllUsers();

            ResponseEntity<Object> response = userService.getAllUsers();

            fail("Exception expected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
