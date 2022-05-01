package com.birthdaynotifier.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    private String id;
    private String emailId;
    private String username;
    private String password;
    private String role;
    private String isEmailIdVerified;
    private String secretCode;
    private String createdAt;
    private String updatedAt;
    private String lastLoggedIn;
    private int recordsCount;

    public static User builder() {
        return new User();
    }

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getEmailId() {
        return emailId;
    }

    public User setEmailId(String emailId) {
        this.emailId = emailId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {
        return role;
    }

    public User setRole(String role) {
        this.role = role;
        return this;
    }

    public String getIsEmailIdVerified() {
        return isEmailIdVerified;
    }

    public User setIsEmailIdVerified(String isEmailIdVerified) {
        this.isEmailIdVerified = isEmailIdVerified;
        return this;
    }

    public String getSecretCode() {
        return secretCode;
    }

    public User setSecretCode(String secretCode) {
        this.secretCode = secretCode;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public User setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public User setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public String getLastLoggedIn() {
        return lastLoggedIn;
    }

    public User setLastLoggedIn(String lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
        return this;
    }

    public int getRecordsCount() {
        return recordsCount;
    }

    public User setRecordsCount(int recordsCount) {
        this.recordsCount = recordsCount;
        return this;
    }

    public User build() {
        return this;
    }

}
