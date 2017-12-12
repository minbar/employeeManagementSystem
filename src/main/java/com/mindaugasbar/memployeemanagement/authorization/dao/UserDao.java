package com.mindaugasbar.memployeemanagement.authorization.dao;

import com.mindaugasbar.memployeemanagement.authorization.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
