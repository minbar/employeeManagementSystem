package com.mindaugasbar.memployeemanagement.authorization.service;

import com.mindaugasbar.memployeemanagement.authorization.domain.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
