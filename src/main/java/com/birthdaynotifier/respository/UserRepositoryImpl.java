package com.birthdaynotifier.respository;

import com.birthdaynotifier.constant.SqlConstant;
import com.birthdaynotifier.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public boolean checkIfUserExistsByEmailId(String emailId) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstant.named_parameter_email_id, emailId);

        int count = jdbcTemplate.queryForObject(SqlConstant.query_count_of_user_by_email_id, params, Integer.class);

        return count != 0;
    }

    public boolean checkIfUserExistsByUsername(String username) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstant.named_parameter_username, username);

        int count = jdbcTemplate.queryForObject(SqlConstant.query_count_of_user_by_username, params, Integer.class);

        return count != 0;
    }

    @Override
    public boolean checkIfUserExistsById(String id) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstant.named_parameter_id, id);

        int count = jdbcTemplate.queryForObject(SqlConstant.query_count_of_user_by_id, params, Integer.class);

        return count != 0;
    }

    @Override
    public User getUserByUsername(String username) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstant.named_parameter_username, username);

        return jdbcTemplate.queryForObject(SqlConstant.query_fetch_user_by_username, params, new UserRowMapper());
    }

    @Override
    public void createNewUser(User user) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstant.named_parameter_id, user.getId());
        params.put(SqlConstant.named_parameter_email_id, user.getEmailId());
        params.put(SqlConstant.named_parameter_username, user.getUsername());
        params.put(SqlConstant.named_parameter_password, user.getPassword());
        params.put(SqlConstant.named_parameter_is_email_id_verified, user.getIsEmailIdVerified());
        params.put(SqlConstant.named_parameter_secret_code, user.getSecretCode());
        params.put(SqlConstant.named_parameter_created_at, user.getCreatedAt());

        jdbcTemplate.update(SqlConstant.query_insert_new_user, params);
    }

    @Override
    public void updateUser(User user) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstant.named_parameter_id, user.getId());
        params.put(SqlConstant.named_parameter_email_id, user.getEmailId());
        params.put(SqlConstant.named_parameter_username, user.getUsername());
        params.put(SqlConstant.named_parameter_password, user.getPassword());
        params.put(SqlConstant.named_parameter_is_email_id_verified, user.getIsEmailIdVerified());
        params.put(SqlConstant.named_parameter_updated_at, user.getUpdatedAt());

        jdbcTemplate.update(SqlConstant.query_update_user, params);
    }

    @Override
    public void deleteUser(int id) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstant.named_parameter_id, String.valueOf(id));

        jdbcTemplate.update(SqlConstant.query_delete_user, params);
    }

    @Override
    public String fetchEncryptedPassword(String username) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstant.named_parameter_username, username);

        return jdbcTemplate.queryForObject(SqlConstant.query_fetch_password, params, String.class);
    }

    @Override
    public String fetchCode(String emailId) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstant.named_parameter_email_id, emailId);

        return jdbcTemplate.queryForObject(SqlConstant.query_fetch_code, params, String.class);
    }

    @Override
    public void updateUserEmailId(User user) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstant.named_parameter_email_id, user.getEmailId());
        params.put(SqlConstant.named_parameter_is_email_id_verified, user.getIsEmailIdVerified());
        params.put(SqlConstant.named_parameter_updated_at, user.getUpdatedAt());

        jdbcTemplate.update(SqlConstant.query_update_user_email_id, params);
    }

    @Override
    public void updateUserPassword(User user) {
        Map<String, String> params = new HashMap<>();
        params.put(SqlConstant.named_parameter_email_id, user.getEmailId());
        params.put(SqlConstant.named_parameter_password, user.getPassword());
        params.put(SqlConstant.named_parameter_updated_at, user.getUpdatedAt());

        jdbcTemplate.update(SqlConstant.query_update_user_password, params);
    }

}

class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();

        user.setId(rs.getString(SqlConstant.column_birthday_id));
        user.setEmailId(rs.getString(SqlConstant.column_birthday_email_id));
        user.setUsername(rs.getString(SqlConstant.column_user_username));
        user.setPassword(rs.getString(SqlConstant.column_user_password));
        user.setIsEmailIdVerified(rs.getString(SqlConstant.column_user_is_email_id_verified));
        user.setSecretCode(rs.getString(SqlConstant.column_user_secret_code));
        user.setCreatedAt(rs.getString(SqlConstant.column_user_created_at));
        user.setUpdatedAt(rs.getString(SqlConstant.column_user_updated_at));

        return user;
    }
}
