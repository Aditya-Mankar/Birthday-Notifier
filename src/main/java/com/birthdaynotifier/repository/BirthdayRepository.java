package com.birthdaynotifier.repository;

import com.birthdaynotifier.constant.Constants;
import com.birthdaynotifier.constant.SqlConstants;
import com.birthdaynotifier.exception.CustomException;
import com.birthdaynotifier.model.Birthday;
import com.birthdaynotifier.utility.Utility;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BirthdayRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public BirthdayRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Birthday> getBirthdaysByEmailId(String emailId) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstants.named_parameter_email_id, emailId);

        return jdbcTemplate.query(SqlConstants.query_fetch_birthdays_by_email_id, params, new BirthdayRowMapper());
    }

    public void addNewBirthday(Birthday birthday) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstants.named_parameter_id, String.valueOf(birthday.getId()));
        params.put(SqlConstants.named_parameter_email_id, birthday.getEmailId());
        params.put(SqlConstants.named_parameter_name, birthday.getName());
        params.put(SqlConstants.named_parameter_birth_date, String.valueOf(birthday.getBirthDate()));
        params.put(SqlConstants.named_parameter_birth_month, String.valueOf(birthday.getBirthMonth()));
        params.put(SqlConstants.named_parameter_remind_before_days, String.valueOf(birthday.getRemindBeforeDays()));
        params.put(SqlConstants.named_parameter_remind_date, String.valueOf(birthday.getRemindDate()));
        params.put(SqlConstants.named_parameter_remind_month, String.valueOf(birthday.getRemindMonth()));
        params.put(SqlConstants.named_parameter_created_at, birthday.getCreatedAt());

        jdbcTemplate.update(SqlConstants.query_insert_new_birthday, params);
    }

    public Boolean checkBirthdayExists(int id) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstants.named_parameter_id, String.valueOf(id));

        int count = jdbcTemplate.queryForObject(SqlConstants.query_count_of_birthday, params, Integer.class);

        return count != 0;
    }

    public void updateBirthday(Birthday birthday) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstants.named_parameter_id, String.valueOf(birthday.getId()));
        params.put(SqlConstants.named_parameter_email_id, birthday.getEmailId());
        params.put(SqlConstants.named_parameter_name, birthday.getName());
        params.put(SqlConstants.named_parameter_birth_date, String.valueOf(birthday.getBirthDate()));
        params.put(SqlConstants.named_parameter_birth_month, String.valueOf(birthday.getBirthMonth()));
        params.put(SqlConstants.named_parameter_remind_before_days, String.valueOf(birthday.getRemindBeforeDays()));
        params.put(SqlConstants.named_parameter_remind_date, String.valueOf(birthday.getRemindDate()));
        params.put(SqlConstants.named_parameter_remind_month, String.valueOf(birthday.getRemindMonth()));
        params.put(SqlConstants.named_parameter_updated_at, birthday.getUpdatedAt());

        jdbcTemplate.update(SqlConstants.query_update_birthday, params);
    }

    public void deleteBirthday(int id) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstants.named_parameter_id, String.valueOf(id));

        jdbcTemplate.update(SqlConstants.query_delete_birthday, params);
    }
}

class BirthdayRowMapper implements RowMapper<Birthday> {

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