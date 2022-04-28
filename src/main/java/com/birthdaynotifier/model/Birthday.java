package com.birthdaynotifier.model;

public class Birthday {

    private String id;
    private String emailId;
    private String name;
    private int birthDate;
    private int birthMonth;
    private int remindBeforeDays;
    private int remindDate;
    private int remindMonth;
    private String createdAt;
    private String updatedAt;

    public static Birthday builder() {
        return new Birthday();
    }

    public String getId() {
        return id;
    }

    public Birthday setId(String id) {
        this.id = id;
        return this;
    }

    public String getEmailId() {
        return emailId;
    }

    public Birthday setEmailId(String emailId) {
        this.emailId = emailId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Birthday setName(String name) {
        this.name = name;
        return this;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public Birthday setBirthDate(int birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public Birthday setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
        return this;
    }

    public int getRemindBeforeDays() {
        return remindBeforeDays;
    }

    public Birthday setRemindBeforeDays(int remindBeforeDays) {
        this.remindBeforeDays = remindBeforeDays;
        return this;
    }

    public int getRemindDate() {
        return remindDate;
    }

    public Birthday setRemindDate(int remindDate) {
        this.remindDate = remindDate;
        return this;
    }

    public int getRemindMonth() {
        return remindMonth;
    }

    public Birthday setRemindMonth(int remindMonth) {
        this.remindMonth = remindMonth;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Birthday setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public Birthday setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public String toString() {
        return "Birthday{" +
                "id='" + id + '\'' +
                ", emailId='" + emailId + '\'' +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", birthMonth=" + birthMonth +
                ", remindBeforeDays=" + remindBeforeDays +
                ", remindDate=" + remindDate +
                ", remindMonth=" + remindMonth +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }

    public Birthday build() {
        return this;
    }

}
