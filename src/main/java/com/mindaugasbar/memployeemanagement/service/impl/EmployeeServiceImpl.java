package com.mindaugasbar.memployeemanagement.service.impl;

import com.mindaugasbar.memployeemanagement.dao.EmployeeDao;
import com.mindaugasbar.memployeemanagement.domain.Employee;
import com.mindaugasbar.memployeemanagement.exceptions.MissingEmployeeException;
import com.mindaugasbar.memployeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

import static java.lang.String.format;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeDao employeeDao;

    @Override
    public List<Employee> getEmployees() {
        return (List<Employee>) employeeDao.findAll();
    }

    @Override
    public Employee getEmployeeByUsername(String name) {
        return employeeDao.findByUsername(name);
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        return employeeDao.findByEmail(email);
    }

    @Transactional
    @Override
    public void deleteEmployee(int id) {
        employeeDao.delete(id);
    }

    @Transactional
    @Override
    public void deleteEmployee(String firstName, String lastName) {
        Employee employee = employeeDao.findByFirstNameAndLastName(firstName, lastName);
        employeeDao.delete(employee.getId());
    }



    @Transactional
    @Override
    public Employee addEmployee(Employee employee) {
        return employeeDao.save(employee);
    }

    @Transactional
    @Override
    public Employee updateEmployeeDetails(Employee employee) throws MissingEmployeeException {
        if(employeeDao.findByUsername(employee.getUsername()) == null) {
            final String message = format("the employee with the first name:[%s], last name:[%s]" +
                "username: [%s]", employee.getFirstName(), employee.getLastName(), employee.getUsername());
            throw new MissingEmployeeException(message);
        }
        return employeeDao.save(employee);
    }



    @Autowired
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
}
