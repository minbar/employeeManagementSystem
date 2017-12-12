package com.mindaugasbar.memployeemanagement.authorization.dao;

import com.mindaugasbar.memployeemanagement.authorization.domain.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleDao extends CrudRepository<Role, Long> {
}
