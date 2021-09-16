package com.birthdaynotifier.constant;

public class Constant {

    public static final String value_true = "true";
    public static final String value_false = "false";

    public static final String date_format = "dd/MM/yyyy";

    public static final String request_path_birthday = "/api/birthday";
    public static final String request_path_birthday_get_all = "/getAll";
    public static final String request_path_birthday_get_email_id = "/get/{emailId}";
    public static final String request_path_birthday_add = "/add";
    public static final String request_path_birthday_update = "/update";
    public static final String request_path_birthday_delete_email_id = "/delete/{id}";
    public static final String request_path_birthday_insert = "/insert";
    public static final String request_path_birthday_modify = "/modify";

    public static final String request_path_user = "/api/user/";
    public static final String request_path_user_get_username = "/get/{username}";
    public static final String request_path_user_add = "/add";
    public static final String request_path_user_update = "/update";
    public static final String request_path_user_delete_id = "/delete/{id}";

    public static final String error_no_birthday_with_email_id = "No records associated with that email id";
    public static final String error_no_birthday_with_id = "No birthday records associated with that id";
    public static final String error_no_user_exists_with_email_id = "No User exists with that email id";
    public static final String error_no_user_exists_with_id = "No User exists with that id";
    public static final String error_id_null_or_empty = "Id cannot be null or empty";
    public static final String error_email_id_null_or_empty = "Email id cannot be null or empty";
    public static final String error_name_null_or_empty = "Name cannot be null or empty";
    public static final String error_birthdate_null_or_empty = "Birth date cannot be null or empty";
    public static final String error_date_null_or_empty = "Date cannot be null or empty";
    public static final String error_month_null_or_empty = "Month cannot be null or empty";
    public static final String error_username_null_or_empty = "Username cannot be null or empty";
    public static final String error_password_null_or_empty = "Password cannot be null or empty";
    public static final String error_user_already_exists_email_id = "User already exists with that email id";
    public static final String error_user_already_exists_username = "User already exists with that username";
    public static final String error_birthday_remind_before_days_null_or_empty = "Remind before days cannot be null or empty";
    public static final String error_birthdate_invalid_format = "Please enter date in dd/mm/yyyy format only";

    public static final String success_birthday_added = "Birthday added successfully";
    public static final String success_birthday_updated = "Birthday updated successfully";
    public static final String success_birthday_deleted = "Birthday deleted successfully";
    public static final String success_user_added = "User added successfully";
    public static final String success_user_updated = "User updated successfully";
    public static final String success_user_deleted = "User deleted successfully";

}
