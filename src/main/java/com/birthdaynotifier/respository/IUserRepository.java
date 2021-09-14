package com.birthdaynotifier.respository;

import com.birthdaynotifier.model.User;

public interface IUserRepository {

    public boolean checkIfUserExistsByEmailId(String emailId);

    public boolean checkIfUserExistsByUsername(String username);

    public User getUserByUsername(String username);

    public void createNewUser(User user);

}
