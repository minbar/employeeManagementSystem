package com.mindaugasbar.memployeemanagement.authorization.dao;

import com.mindaugasbar.memployeemanagement.authorization.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findById(long id);

    void deleteById(long id);
}
