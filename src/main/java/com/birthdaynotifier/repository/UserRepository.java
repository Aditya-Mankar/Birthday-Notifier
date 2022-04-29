package com.birthdaynotifier.repository;

import com.birthdaynotifier.constant.SqlConstants;
import com.birthdaynotifier.mapper.UserRowMapper;
import com.birthdaynotifier.model.User;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Boolean validateUserByEmailId(String emailId) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstants.named_parameter_email_id, emailId);

        int count = jdbcTemplate.queryForObject(SqlConstants.query_count_of_user_by_email_id, params, Integer.class);

        return count == 0;
    }

    public Boolean validateUserByUsername(String username) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstants.named_parameter_username, username);

        int count = jdbcTemplate.queryForObject(SqlConstants.query_count_of_user_by_username, params, Integer.class);

        return count == 0;
    }

    public User getUserByUsername(String username) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstants.named_parameter_username, username);

        return jdbcTemplate.queryForObject(SqlConstants.query_fetch_user_by_username, params, new UserRowMapper());
    }

    public void createUser(User user) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstants.named_parameter_id, user.getId());
        params.put(SqlConstants.named_parameter_email_id, user.getEmailId());
        params.put(SqlConstants.named_parameter_username, user.getUsername());
        params.put(SqlConstants.named_parameter_password, user.getPassword());
        params.put(SqlConstants.named_parameter_is_email_id_verified, user.getIsEmailIdVerified());
        params.put(SqlConstants.named_parameter_secret_code, user.getSecretCode());
        params.put(SqlConstants.named_parameter_created_at, user.getCreatedAt());
        params.put(SqlConstants.named_parameter_role, user.getRole());

        jdbcTemplate.update(SqlConstants.query_insert_new_user, params);
    }

    public String fetchCode(String emailId) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstants.named_parameter_email_id, emailId);

        return jdbcTemplate.queryForObject(SqlConstants.query_fetch_code, params, String.class);
    }

    public void verifyEmailId(User user) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstants.named_parameter_email_id, user.getEmailId());
        params.put(SqlConstants.named_parameter_is_email_id_verified, user.getIsEmailIdVerified());
        params.put(SqlConstants.named_parameter_updated_at, user.getUpdatedAt());

        jdbcTemplate.update(SqlConstants.query_verify_user, params);
    }

    public void updatePassword(User user) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstants.named_parameter_email_id, user.getEmailId());
        params.put(SqlConstants.named_parameter_password, user.getPassword());
        params.put(SqlConstants.named_parameter_updated_at, user.getUpdatedAt());

        jdbcTemplate.update(SqlConstants.query_update_user_password, params);
    }

    public List<User> getAllUsers() {
        return jdbcTemplate.query(SqlConstants.query_fetch_all_users, new UserRowMapper());
    }

    public int getRecordsCountForUser(String emailId) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstants.named_parameter_email_id, emailId);

        return jdbcTemplate.queryForObject(SqlConstants.query_fetch_records_count_for_user, params, Integer.class);
    }

    public void deleteUserByEmailId(String emailId) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstants.named_parameter_email_id, emailId);

        jdbcTemplate.update(SqlConstants.query_delete_user_by_email_id, params);
    }

}
