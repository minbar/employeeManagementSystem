package com.mindaugasbar.memployeemanagement.authorization.service.impl;

import com.mindaugasbar.memployeemanagement.authorization.dao.RoleDao;
import com.mindaugasbar.memployeemanagement.authorization.dao.UserDao;
import com.mindaugasbar.memployeemanagement.authorization.domain.Role;
import com.mindaugasbar.memployeemanagement.authorization.domain.User;
import com.mindaugasbar.memployeemanagement.authorization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private RoleDao roleDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        @SuppressWarnings(name = "uncheked");
        user.setRoles(new HashSet<>(roleDao.findAll()));
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