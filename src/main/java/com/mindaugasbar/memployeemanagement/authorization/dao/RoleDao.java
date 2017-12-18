package com.mindaugasbar.memployeemanagement.authorization.dao;

import com.mindaugasbar.memployeemanagement.authorization.domain.Role;
import com.mindaugasbar.memployeemanagement.exceptions.RoleNotFoundException;
import org.springframework.data.repository.CrudRepository;

public interface RoleDao extends CrudRepository<Role, Long> {

    Role findByName(String name);
}
