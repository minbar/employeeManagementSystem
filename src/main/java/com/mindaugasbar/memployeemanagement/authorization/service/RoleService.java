package com.mindaugasbar.memployeemanagement.authorization.service;

import com.mindaugasbar.memployeemanagement.authorization.domain.Role;

import java.util.List;

public interface RoleService {

    Role findByName(String roleName);

    List<String> getAllRoleNames();
}
