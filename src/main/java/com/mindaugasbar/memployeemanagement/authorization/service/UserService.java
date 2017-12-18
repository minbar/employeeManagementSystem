package com.mindaugasbar.memployeemanagement.authorization.service;

import com.mindaugasbar.memployeemanagement.authorization.domain.Role;
import com.mindaugasbar.memployeemanagement.authorization.domain.User;

public interface UserService {

    void save(User user, String role);

    User findByUsername(String username);
}
