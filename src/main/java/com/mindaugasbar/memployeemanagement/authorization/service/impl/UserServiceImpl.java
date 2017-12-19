package com.mindaugasbar.memployeemanagement.authorization.service.impl;

import com.mindaugasbar.memployeemanagement.authorization.dao.RoleDao;
import com.mindaugasbar.memployeemanagement.authorization.dao.UserDao;
import com.mindaugasbar.memployeemanagement.authorization.domain.Role;
import com.mindaugasbar.memployeemanagement.authorization.domain.User;
import com.mindaugasbar.memployeemanagement.authorization.service.RoleService;
import com.mindaugasbar.memployeemanagement.authorization.service.UserService;
import com.mindaugasbar.memployeemanagement.employees.domain.Employee;
import com.mindaugasbar.memployeemanagement.exceptions.MissingUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PreRemove;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private RoleService roleService;
    @Override
    public void save(User user, String roleName) {
        Objects.requireNonNull(roleName);
        user.setPassword(user.getPassword());
        Role role = roleService.findByName(roleName);
        Objects.requireNonNull(role);
        user.setRole(role);

        userDao.save(user);
    }

    @Override
    public List<User> getUsers() {
        return (List<User>) userDao.findAll();
    }

    @Override
    public User findById(long id) {
        return userDao.findById(id);
    }

    @Transactional
    @Override
    public void updateUser(User user) throws MissingUserException {
        checkUserExists(user.getId());
        userDao.save(user);
    }

    @Transactional
    @Override
    public void deleteById(long id) throws MissingUserException {
        checkUserExists(id);
        User user = findById(id);
        userDao.deleteById(user.getId());
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
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    private void checkUserExists(long id) throws MissingUserException {
        if(findById(id) == null) {
            String message = format("the user with id:[%d] could not be found", id);
            throw new MissingUserException(message);
        }
    }


}