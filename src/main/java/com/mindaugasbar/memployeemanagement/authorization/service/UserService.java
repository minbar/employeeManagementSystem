package com.mindaugasbar.memployeemanagement.authorization.service;

import com.mindaugasbar.memployeemanagement.authorization.domain.Role;
import com.mindaugasbar.memployeemanagement.authorization.domain.User;

import java.util.List;

public interface UserService {

    void save(User user, String role);

    /**
     * finds the employees by username.
     * @param username username.
     * @return a list of users.
     */
    User findByUsername(String username);

    /**
     * gets all of the users of the company
     * @return a list of users.
     */
    List<User> getUsers();
}
