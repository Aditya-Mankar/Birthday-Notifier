package com.birthdaynotifier.repository;

import com.birthdaynotifier.constant.Constants;
import com.birthdaynotifier.model.User;
import com.birthdaynotifier.utility.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .setId(Utility.generateUUID(3))
                .setEmailId("test" + Utility.generateUUID(3) + "@gmail.com")
                .setUsername("test" + Utility.generateUUID(3))
                .setPassword(bCryptPasswordEncoder.encode("test"))
                .setRole(Constants.role_user)
                .setIsEmailIdVerified(Constants.value_false)
                .setSecretCode(Utility.generateCode(7))
                .setCreatedAt(Instant.now().toString())
                .setUpdatedAt("")
                .setRecordsCount(0)
                .build();

        System.out.println("New user: " + user);
    }

    @Test
    @DisplayName("Test to check if user exists using email id")
    void shouldValidateUserByEmailId() {
        userRepository.createUser(user);

        assertThat(userRepository.validateUserByEmailId(user.getEmailId())).isFalse();
    }

    @Test
    @DisplayName("Test to check if user exists using username")
    void shouldValidateUserByUsername() {
        userRepository.createUser(user);

        assertThat(userRepository.validateUserByUsername(user.getUsername())).isFalse();
    }

    @Test
    @DisplayName("Test to check if fetching user by username is successful")
    void shouldGetUserByUsername() {
        userRepository.createUser(user);

        User createdUser = userRepository.getUserByUsername(user.getUsername());

        assertThat(createdUser.getId().equals(user.getId())).isTrue();
    }

    @Test
    @DisplayName("Test to create user")
    void shouldCreateUser() {
        userRepository.createUser(user);

        User createdUser = userRepository.getUserByUsername(user.getUsername());

        assertThat(createdUser.getId().equals(user.getId())).isTrue();
    }

    @Test
    @DisplayName("Test to fetch user's code")
    void shouldFetchCode() {
        userRepository.createUser(user);

        assertThat(user.getSecretCode().equals(userRepository.fetchCode(user.getEmailId()))).isTrue();
    }

    @Test
    @DisplayName("Test to check if user's account can be verified")
    void shouldVerifyEmailId() {
        userRepository.createUser(user);

        user.setIsEmailIdVerified(Constants.value_true);

        userRepository.verifyEmailId(user);

        User updatedUser = userRepository.getUserByUsername(user.getUsername());

        assertThat(updatedUser.getIsEmailIdVerified().equals(Constants.value_true)).isTrue();
    }

    @Test
    @DisplayName("Test to update user's password")
    void shouldUpdatePassword() {
        userRepository.createUser(user);

        String newPassword = bCryptPasswordEncoder.encode("test new");
        user.setPassword(newPassword);

        userRepository.updatePassword(user);

        User updatedUser = userRepository.getUserByUsername(user.getUsername());

        assertThat(updatedUser.getPassword().equals(newPassword)).isTrue();
    }

    @Test
    @DisplayName("Test to fetch all users")
    void shouldGetAllUsers() {
        List<User> usersList = userRepository.getAllUsers();

        assertThat(usersList.size() > 0).isTrue();
    }

    @Test
    @DisplayName("Test to fetch birthday records count for a user")
    void shouldGetRecordsCountForUser() {
        userRepository.createUser(user);

        assertThat(userRepository.getRecordsCountForUser(user.getEmailId()) >= 0).isTrue();
    }

    @Test
    @DisplayName("Test to delete user by email id")
    void shouldDeleteUserByEmailId() {
        userRepository.createUser(user);

        userRepository.deleteUserByEmailId(user.getEmailId());

        assertThat(userRepository.validateUserByEmailId(user.getEmailId())).isTrue();
    }

    @Test
    @DisplayName("Test to update user's last logged in")
    void shouldUpdateUserLastLoggedIn() {
        userRepository.createUser(user);

        userRepository.updateUserLastLoggedIn(user.getUsername());

        User updatedUser = userRepository.getUserByUsername(user.getUsername());

        assertThat(Utility.checkIfNullOrEmpty(updatedUser.getLastLoggedIn())).isFalse();
    }

}