package com.birthdaynotifier.respository;

import com.birthdaynotifier.model.User;

public interface IUserRepository {

    public boolean checkIfUserExistsByEmailId(String emailId);

    public boolean checkIfUserExistsByUsername(String username);

    public boolean checkIfUserExistsById(String id);

    public User getUserByUsername(String username);

    public void createNewUser(User user);

    public void updateUser(User user);

    public void deleteUser(int id);

    public String fetchEncryptedPassword(String username);

    public String fetchCode(String emailId);

    public void updateUserEmailId(User user);

    public void updateUserPassword(User user);
}
