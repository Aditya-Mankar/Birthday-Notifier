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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    public int getRemindBeforeDays() {
        return remindBeforeDays;
    }

    public void setRemindBeforeDays(int remindBeforeDays) {
        this.remindBeforeDays = remindBeforeDays;
    }

    public int getRemindDate() {
        return remindDate;
    }

    public void setRemindDate(int remindDate) {
        this.remindDate = remindDate;
    }

    public int getRemindMonth() {
        return remindMonth;
    }

    public void setRemindMonth(int remindMonth) {
        this.remindMonth = remindMonth;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
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

}
