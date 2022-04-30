package com.birthdaynotifier.mapper;

import com.birthdaynotifier.constant.SqlConstants;
import com.birthdaynotifier.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();

        user.setId(rs.getString(SqlConstants.column_birthday_id));
        user.setEmailId(rs.getString(SqlConstants.column_birthday_email_id));
        user.setUsername(rs.getString(SqlConstants.column_user_username));
        user.setPassword(rs.getString(SqlConstants.column_user_password));
        user.setRole(rs.getString(SqlConstants.column_user_role));
        user.setIsEmailIdVerified(rs.getString(SqlConstants.column_user_is_email_id_verified));
        user.setCreatedAt(rs.getString(SqlConstants.column_user_created_at));
        user.setUpdatedAt(rs.getString(SqlConstants.column_user_updated_at));
        user.setLastLoggedIn(rs.getString(SqlConstants.column_user_last_logged_in));

        return user;
    }
}
