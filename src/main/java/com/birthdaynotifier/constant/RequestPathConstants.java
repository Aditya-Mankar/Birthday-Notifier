package com.birthdaynotifier.constant;

public class RequestPathConstants {

    public static final String birthday_controller = "/api/v1/birthday";
    public static final String read_by_email_id = "/read/{emailId}";
    public static final String insert_birthday = "/insert";
    public static final String modify = "/modify";
    public static final String delete_by_id = "/delete/{id}";

    public static final String user_controller = "/api/v1/user";
    public static final String read_by_username = "/read/{username}";
    public static final String insert_user = "/insert";
    public static final String verify_email_id = "/verify-email-id";
    public static final String update_password = "/update-password";

    public static final String mail_controller = "/api/v1/mail";
    public static final String send_code = "/sendCode/{emailId}";

}
