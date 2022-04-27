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
        Birthday birthday = new Birthday();

        birthday.setId(rs.getString(SqlConstants.column_birthday_id));
        birthday.setEmailId(rs.getString(SqlConstants.column_birthday_email_id));
        birthday.setName(rs.getString(SqlConstants.column_birthday_name));
        birthday.setBirthDate(rs.getInt(SqlConstants.column_birthday_birth_date));
        birthday.setBirthMonth(rs.getInt(SqlConstants.column_birthday_birth_month));
        birthday.setRemindBeforeDays(rs.getInt(SqlConstants.column_birthday_remind_before_days));
        birthday.setRemindDate(rs.getInt(SqlConstants.column_birthday_remind_date));
        birthday.setRemindMonth(rs.getInt(SqlConstants.column_birthday_remind_month));
        birthday.setCreatedAt(rs.getString(SqlConstants.column_birthday_created_at));
        birthday.setUpdatedAt(Utility.checkIfNullOrEmpty(rs.getString(SqlConstants.column_birthday_updated_at)) ?
                rs.getString(SqlConstants.column_birthday_created_at) : rs.getString(SqlConstants.column_birthday_updated_at));

        return birthday;
    }

}
