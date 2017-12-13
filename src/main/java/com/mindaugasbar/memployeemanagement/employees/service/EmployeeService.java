package com.mindaugasbar.memployeemanagement.employees.service;

import com.mindaugasbar.memployeemanagement.employees.domain.Employee;
import com.mindaugasbar.memployeemanagement.exceptions.MissingEmployeeException;

import java.util.List;

public interface EmployeeService {


    /**
     * gets all the employees of the organization.
     * @return the employee list.
     */
    List<Employee> getEmployees();
    /**
     * finds a given employee by it's name.
     * @param id the employee's name.
     * @return the employee if found, otherwise null.
     */
    Employee getEmployeeById(long id);

    /**
     * finds a given employee by it's email.
     * @param email the email.
     * @return the employee if found, otherwise null.
     */
    Employee getEmployeeByEmail(String email);

    /**
     * delete the given employee.
     * @param id the employee id.
     */
    void deleteEmployee(long id);

    /**
     * deletes the emplyee.
     * @param firstName the employees first name.
     * @param lastName the employees last name.
     */
    void deleteEmployee(String firstName, String lastName);

    /**
     * adds the provided employee to the database.
     * @param employee the employee object.
     * @return the added employee.
     */
    Employee addEmployee(Employee employee);

    /**
     * updates the given employee
     * @param employee the employee.
     * @return the update employee.
     */
    Employee updateEmployeeDetails(Employee employee) throws MissingEmployeeException;

    Employee updateEmployeeNonNullFields(Employee employee) throws MissingEmployeeException;
}
