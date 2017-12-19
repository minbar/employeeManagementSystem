package com.mindaugasbar.memployeemanagement.employees.dao;

import com.mindaugasbar.memployeemanagement.authorization.domain.User;
import com.mindaugasbar.memployeemanagement.employees.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

    /**
     * Find the given employee by their username.
     * @param id the employee's id.
     * @return the Employee or null if not found.
     */
    Employee findById(long id);

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

    /**
     *  gets the age of all employees from the company.
     */
    @Query("select age from Employee")
    List<Integer> getAgeOfAllEmployees();

    @Query("select startedWorkingDate from Employee where id = :id")
    LocalDate getBeginningTimeById(long id);

    List<Employee> findAllByUserNull();
}
