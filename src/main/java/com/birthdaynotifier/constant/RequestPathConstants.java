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
    public static final String complete_delete = "/user-complete-delete";

    public static final String mail_controller = "/api/v1/mail";
    public static final String send_code = "/sendCode/{emailId}";

    public static final String admin_controller = "/api/v1/admin";
    public static final String read_all_users = "/get-users";
    public static final String admin_user_complete_delete = "/user-complete-delete/{emailId}";

    public static final String authenticate = "/api/v1/authenticate";

    public static final String[] user_permitted_paths = {"/api/v1/user/**", "/api/v1/birthday/**", "/api/v1/mail/**"};
    public static final String admin_permitted_paths = "/api/v1/admin/**";
    public static final String[] auth_paths = {"/api/v1/authenticate", "/api/v1/user/insert", "/api/v1/mail/sendCode/{emailId}", "/api/v1/user/update-password"};

}
