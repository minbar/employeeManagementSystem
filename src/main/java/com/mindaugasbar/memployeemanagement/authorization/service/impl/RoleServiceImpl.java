package com.mindaugasbar.memployeemanagement.authorization.service.impl;

import com.mindaugasbar.memployeemanagement.authorization.dao.RoleDao;
import com.mindaugasbar.memployeemanagement.authorization.domain.Role;
import com.mindaugasbar.memployeemanagement.authorization.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    @Override
    public Role findByName(String roleName) {
        return roleDao.findByName(roleName);
    }

    @Override
    public List<String> getAllRoleNames() {
        return roleDao.findAllRoleNames();
    }

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
}
