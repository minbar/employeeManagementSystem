package com.mindaugasbar.memployeemanagement.authorization.dao;

import com.mindaugasbar.memployeemanagement.authorization.domain.Role;
import com.mindaugasbar.memployeemanagement.exceptions.RoleNotFoundException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleDao extends CrudRepository<Role, Long> {

    Role findByName(String name);

    @Query("SELECT name from Role")
    List<String> findAllRoleNames();
}
