package com.birthdaynotifier.mapper;

import com.birthdaynotifier.constant.SqlConstants;
import com.birthdaynotifier.model.Birthday;
import com.birthdaynotifier.utility.Utility;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BirthdayRowMapper implements RowMapper<Birthday> {

    @Override
    public Birthday mapRow(ResultSet rs, int i) throws SQLException {
        return Birthday.builder()
                .setId(rs.getString(SqlConstants.column_birthday_id))
                .setEmailId(rs.getString(SqlConstants.column_birthday_email_id))
                .setName(rs.getString(SqlConstants.column_birthday_name))
                .setBirthDate(rs.getInt(SqlConstants.column_birthday_birth_date))
                .setBirthMonth(rs.getInt(SqlConstants.column_birthday_birth_month))
                .setRemindBeforeDays(rs.getInt(SqlConstants.column_birthday_remind_before_days))
                .setRemindDate(rs.getInt(SqlConstants.column_birthday_remind_date))
                .setRemindMonth(rs.getInt(SqlConstants.column_birthday_remind_month))
                .setCreatedAt(rs.getString(SqlConstants.column_birthday_created_at))
                .setUpdatedAt(Utility.checkIfNullOrEmpty(rs.getString(SqlConstants.column_birthday_updated_at)) ?
                        rs.getString(SqlConstants.column_birthday_created_at) : rs.getString(SqlConstants.column_birthday_updated_at))
                .build();
    }

}
