package com.mindaugasbar.memployeemanagement.dao;

import com.mindaugasbar.memployeemanagement.domain.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeDao extends CrudRepository<Employee, Integer> {

    /**
     * Find the given employee by their username.
     * @param username the employee's username.
     * @return the Employee or null if not found.
     */
    Employee findByUsername(String username);

    /**
     * Find the given employee by their email.
     * @param email the employee's email.
     * @return the Employee or null if not found.
     */
    Employee findByEmail(String email);

    /**
     * Find the given employee by their full name.
     * @param firstName the employee's first name.
     * @param lastName the employee's last name.
     * @return the found employee, or null otherwise.
     */
    Employee findByFirstNameAndLastName(String firstName, String lastName);
}
