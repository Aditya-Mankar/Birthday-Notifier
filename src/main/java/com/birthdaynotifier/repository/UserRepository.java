package com.birthdaynotifier.repository;

import com.birthdaynotifier.constant.SqlConstants;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Boolean validateEmailId(String emailId) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstants.named_parameter_email_id, emailId);

        int count = jdbcTemplate.queryForObject(SqlConstants.query_count_of_email_id, params, Integer.class);

        return count == 0;
    }

}
