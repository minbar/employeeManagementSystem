package com.mindaugasbar.memployeemanagement.employees.service.impl;

import com.mindaugasbar.memployeemanagement.employees.dao.EmployeeDao;
import com.mindaugasbar.memployeemanagement.employees.domain.Employee;
import com.mindaugasbar.memployeemanagement.employees.service.EmployeeService;
import com.mindaugasbar.memployeemanagement.exceptions.MissingEmployeeException;
import com.mindaugasbar.memployeemanagement.exceptions.WrongDateFormatException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.String.format;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeDao employeeDao;

    @Override
    public List<Employee> getEmployees() {
        return (List<Employee>) employeeDao.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
        return employeeDao.findById(id);
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        return employeeDao.findByEmail(email);
    }

    @Transactional
    @Override
    public void deleteEmployee(long id) {
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
        checkEmployeeExistsById(employee.getId());
        return employeeDao.save(employee);
    }

    @Transactional
    @Override
    public Employee updateEmployeeNonNullFields(Employee employee) throws MissingEmployeeException {
        Employee existingEmployee = checkEmployeeExistsById(employee.getId());
        copyNonNullProperties(employee, existingEmployee);
        return employeeDao.save(existingEmployee);
    }

    private static void copyNonNullProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    private static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }


    private Employee checkEmployeeExistsById(long id) throws MissingEmployeeException {
        Employee employee = employeeDao.findById(id);
        if (employee == null) {
            final String message = format("the employee with the first name:[%s], last name:[%s]" +
                    "username: [%s]", employee.getFirstName(), employee.getLastName(), employee.getUser().getUsername());
            throw new MissingEmployeeException(message);
        }
        return employee;
    }





    @Autowired
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
}
