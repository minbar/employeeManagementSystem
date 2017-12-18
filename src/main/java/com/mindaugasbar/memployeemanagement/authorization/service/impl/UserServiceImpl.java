package com.mindaugasbar.memployeemanagement.authorization.service.impl;

import com.mindaugasbar.memployeemanagement.authorization.dao.RoleDao;
import com.mindaugasbar.memployeemanagement.authorization.dao.UserDao;
import com.mindaugasbar.memployeemanagement.authorization.domain.Role;
import com.mindaugasbar.memployeemanagement.authorization.domain.User;
import com.mindaugasbar.memployeemanagement.authorization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private RoleDao roleDao;
    @Override
    public void save(User user, String roleName) {
        user.setPassword(user.getPassword());
        Role role = roleDao.findByName(roleName);
        Objects.requireNonNull(roleName);
        user.setRole(role);

        userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);

    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
}