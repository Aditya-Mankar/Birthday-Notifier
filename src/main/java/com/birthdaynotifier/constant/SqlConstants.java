package com.birthdaynotifier.constant;

public class SqlConstants {

    public static final String query_fetch_birthdays_by_email_id = "SELECT * FROM birthdays_table WHERE email_id = :emailId ORDER BY name";
    public static final String query_insert_new_birthday = "INSERT INTO birthdays_table(id, email_id, name, birth_date, birth_month, remind_before_days, remind_date, remind_month, created_at) VALUES(:id, :emailId, :name, :birthDate, :birthMonth, :remindBeforeDays, :remindDate, :remindMonth, :createdAt)";
    public static final String query_count_of_birthday = "SELECT count(*) FROM birthdays_table WHERE id = :id";
    public static final String query_update_birthday = "UPDATE birthdays_table SET email_id = :emailId, name = :name, birth_date = :birthDate, birth_month = :birthMonth, remind_before_days = :remindBeforeDays, remind_date = :remindDate, remind_month = :remindMonth, updated_at = :updatedAt WHERE id = :id";
    public static final String query_delete_birthday = "DELETE FROM birthdays_table WHERE id = :id";
    public static final String query_fetch_birthdays_by_date_and_month = "SELECT * FROM birthdays_table WHERE remind_date = :birthDate AND remind_month = :birthMonth";
    public static final String query_delete_birthdays_by_email_id = "DELETE FROM birthdays_table WHERE email_id = :emailId";

    public static final String query_count_of_user_by_email_id = "SELECT count(*) FROM users_table WHERE email_id = :emailId";
    public static final String query_count_of_user_by_username = "SELECT count(*) FROM users_table WHERE username = :username";
    public static final String query_fetch_user_by_username = "SELECT * FROM users_table WHERE username = :username";
    public static final String query_insert_new_user = "INSERT INTO users_table(id, email_id, username, password, is_email_id_verified, secret_code, created_at, role) VALUES(:id, :emailId, :username, :password, :isEmailIdVerified, :secretCode, :createdAt, :role)";
    public static final String query_fetch_code = "SELECT secret_code FROM users_table WHERE email_id = :emailId";
    public static final String query_verify_user = "UPDATE users_table SET is_email_id_verified = :isEmailIdVerified, updated_at = :updatedAt WHERE email_id = :emailId";
    public static final String query_update_user_password = "UPDATE users_table SET password = :password, updated_at = :updatedAt WHERE email_id = :emailId";
    public static final String query_fetch_all_users = "SELECT * FROM users_table";
    public static final String query_fetch_records_count_for_user = "SELECT count(*) FROM users_table ut INNER JOIN birthdays_table bt ON ut.email_id = bt.email_id WHERE ut.email_id = :emailId";
    public static final String query_delete_user_by_email_id = "DELETE FROM users_table WHERE email_id = :emailId";
    public static final String query_update_user_last_logged_in = "UPDATE users_table SET last_logged_in = :date WHERE username = :username";

    public static final String named_parameter_id = "id";
    public static final String named_parameter_name = "name";
    public static final String named_parameter_birth_date = "birthDate";
    public static final String named_parameter_birth_month = "birthMonth";
    public static final String named_parameter_remind_date = "remindDate";
    public static final String named_parameter_remind_month = "remindMonth";
    public static final String named_parameter_created_at = "createdAt";
    public static final String named_parameter_updated_at = "updatedAt";
    public static final String named_parameter_remind_before_days = "remindBeforeDays";

    public static final String named_parameter_email_id = "emailId";
    public static final String named_parameter_username = "username";
    public static final String named_parameter_password = "password";
    public static final String named_parameter_is_email_id_verified = "isEmailIdVerified";
    public static final String named_parameter_secret_code = "secretCode";
    public static final String named_parameter_role = "role";
    public static final String named_parameter_date = "date";

    public static final String column_birthday_id = "id";
    public static final String column_birthday_email_id = "email_id";
    public static final String column_birthday_name = "name";
    public static final String column_birthday_birth_date = "birth_date";
    public static final String column_birthday_birth_month = "birth_month";
    public static final String column_birthday_remind_date = "remind_date";
    public static final String column_birthday_remind_month = "remind_month";
    public static final String column_birthday_created_at = "created_at";
    public static final String column_birthday_updated_at = "updated_at";
    public static final String column_birthday_remind_before_days = "remind_before_days";

    public static final String column_user_username = "username";
    public static final String column_user_password = "password";
    public static final String column_user_is_email_id_verified = "is_email_id_verified";
    public static final String column_user_secret_code = "secret_code";
    public static final String column_user_created_at = "created_at";
    public static final String column_user_updated_at = "updated_at";
    public static final String column_user_role = "role";
    public static final String column_user_last_logged_in = "last_logged_in";

}
