package com.birthdaynotifier.mapper;

import com.birthdaynotifier.constant.SqlConstants;
import com.birthdaynotifier.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        return User.builder()
                .setId(rs.getString(SqlConstants.column_birthday_id))
                .setEmailId(rs.getString(SqlConstants.column_birthday_email_id))
                .setUsername(rs.getString(SqlConstants.column_user_username))
                .setPassword(rs.getString(SqlConstants.column_user_password))
                .setRole(rs.getString(SqlConstants.column_user_role))
                .setIsEmailIdVerified(rs.getString(SqlConstants.column_user_is_email_id_verified))
                .setCreatedAt(rs.getString(SqlConstants.column_user_created_at))
                .setUpdatedAt(rs.getString(SqlConstants.column_user_updated_at))
                .setLastLoggedIn(rs.getString(SqlConstants.column_user_last_logged_in))
                .build();
    }
}
