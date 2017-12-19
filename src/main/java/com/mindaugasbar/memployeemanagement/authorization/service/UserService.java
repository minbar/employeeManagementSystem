package com.mindaugasbar.memployeemanagement.authorization.service;

import com.mindaugasbar.memployeemanagement.authorization.domain.Role;
import com.mindaugasbar.memployeemanagement.authorization.domain.User;
import com.mindaugasbar.memployeemanagement.exceptions.MissingUserException;

import java.util.List;

public interface UserService {

    void save(User user, String role);

    /**
     * finds the user by username.
     * @param username username.
     * @return a list of users.
     */
    User findByUsername(String username);

    /**
     *  Finds the user by id.
     * @param id the id.
     * @return the corresponding user.
     */
    User findById(long id);

    /**
     * gets all of the users of the company
     * @return a list of users.
     */
    List<User> getUsers();

    /**
     * updates the given user
     * @param user the user.
     * @throws MissingUserException is thrown if a user with such an id does not exist.
     */
    void updateUser(User user) throws MissingUserException;

    /**
     * Delete the given user.
     * @param id the given user.
     * @throws MissingUserException the missing user exception.
     */
    void deleteById(long id) throws MissingUserException;
}
