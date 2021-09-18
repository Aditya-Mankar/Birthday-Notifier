package com.birthdaynotifier.respository;

import com.birthdaynotifier.constant.Constant;
import com.birthdaynotifier.constant.SqlConstant;
import com.birthdaynotifier.exception.CustomException;
import com.birthdaynotifier.model.Birthday;
import com.birthdaynotifier.utility.Utility;
import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BirthdayRepository implements IBirthdayRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Birthday> getAllBirthdays() {
        return jdbcTemplate.query(SqlConstant.query_fetch_all_birthdays, new BirthdayRowMapper());
    }

    @Override
    public void validateEmailId(String emailId) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstant.named_parameter_email_id, emailId);

        int count = jdbcTemplate.queryForObject(SqlConstant.query_count_of_email_id, params, Integer.class);

        if(count == 0)
            throw new CustomException(Constant.error_no_birthday_with_email_id);
    }

    @Override
    public List<Birthday> getBirthdaysByEmailId(String emailId) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstant.named_parameter_email_id, emailId);

        return jdbcTemplate.query(SqlConstant.query_fetch_birthdays_by_email_id, params, new BirthdayRowMapper());
    }

    @Override
    public void addNewBirthday(Birthday birthday) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstant.named_parameter_id, String.valueOf(birthday.getId()));
        params.put(SqlConstant.named_parameter_email_id, birthday.getEmailId());
        params.put(SqlConstant.named_parameter_name, birthday.getName());
        params.put(SqlConstant.named_parameter_birth_date, String.valueOf(birthday.getBirthDate()));
        params.put(SqlConstant.named_parameter_birth_month, String.valueOf(birthday.getBirthMonth()));
        params.put(SqlConstant.named_parameter_remind_before_days, String.valueOf(birthday.getRemindBeforeDays()));
        params.put(SqlConstant.named_parameter_remind_date, String.valueOf(birthday.getRemindDate()));
        params.put(SqlConstant.named_parameter_remind_month, String.valueOf(birthday.getRemindMonth()));
        params.put(SqlConstant.named_parameter_created_at, birthday.getCreatedAt());

        jdbcTemplate.update(SqlConstant.query_insert_new_birthday, params);
    }

    @Override
    public void updateBirthday(Birthday birthday) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstant.named_parameter_id, String.valueOf(birthday.getId()));
        params.put(SqlConstant.named_parameter_email_id, birthday.getEmailId());
        params.put(SqlConstant.named_parameter_name, birthday.getName());
        params.put(SqlConstant.named_parameter_birth_date, String.valueOf(birthday.getBirthDate()));
        params.put(SqlConstant.named_parameter_birth_month, String.valueOf(birthday.getBirthMonth()));
        params.put(SqlConstant.named_parameter_remind_before_days, String.valueOf(birthday.getRemindBeforeDays()));
        params.put(SqlConstant.named_parameter_remind_date, String.valueOf(birthday.getRemindDate()));
        params.put(SqlConstant.named_parameter_remind_month, String.valueOf(birthday.getRemindMonth()));
        params.put(SqlConstant.named_parameter_updated_at, birthday.getUpdatedAt());

        jdbcTemplate.update(SqlConstant.query_update_birthday, params);
    }

    @Override
    public void checkBirthdayExists(int id) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstant.named_parameter_id, String.valueOf(id));

        int count = jdbcTemplate.queryForObject(SqlConstant.query_count_of_birthday, params, Integer.class);

        if(count == 0)
            throw new CustomException(Constant.error_no_birthday_with_id);
    }

    @Override
    public void deleteBirthday(int id) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstant.named_parameter_id, String.valueOf(id));

        jdbcTemplate.update(SqlConstant.query_delete_birthday, params);
    }

    @Override
    public List<Birthday> getBirthdaysByDateAndMonth(Birthday request) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstant.named_parameter_birth_date, String.valueOf(request.getBirthDate()));
        params.put(SqlConstant.named_parameter_birth_month, String.valueOf(request.getBirthMonth()));

        return jdbcTemplate.query(SqlConstant.query_fetch_birthdays_by_date_and_month, params, new BirthdayRowMapper());
    }

}

class BirthdayRowMapper implements RowMapper<Birthday> {

    @Override
    public Birthday mapRow(ResultSet rs, int i) throws SQLException {
        Birthday birthday = new Birthday();

        birthday.setId(rs.getString(SqlConstant.column_birthday_id));
        birthday.setEmailId(rs.getString(SqlConstant.column_birthday_email_id));
        birthday.setName(rs.getString(SqlConstant.column_birthday_name));
        birthday.setBirthDate(rs.getInt(SqlConstant.column_birthday_birth_date));
        birthday.setBirthMonth(rs.getInt(SqlConstant.column_birthday_birth_month));
        birthday.setRemindBeforeDays(rs.getInt(SqlConstant.column_birthday_remind_before_days));
        birthday.setRemindDate(rs.getInt(SqlConstant.column_birthday_remind_date));
        birthday.setRemindMonth(rs.getInt(SqlConstant.column_birthday_remind_month));
        birthday.setCreatedAt(rs.getString(SqlConstant.column_birthday_created_at));
        birthday.setUpdatedAt(Utility.checkIfNullOrEmpty(rs.getString(SqlConstant.column_birthday_updated_at)) ?
                null : rs.getString(SqlConstant.column_birthday_updated_at));

        return birthday;
    }

}
