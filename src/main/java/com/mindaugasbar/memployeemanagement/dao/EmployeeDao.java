package com.mindaugasbar.memployeemanagement.dao;

import com.mindaugasbar.memployeemanagement.domain.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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

    /**
     * gets the sum of all vacation days available to all employees of a company.
     * @return the full sum of vacation days available.
     */
    @Query("select sum(earnedVacationDays) from Employee")
    int findSumOfVacationDays();

    /**
     * Gets all the employees who have more vacation days than the number provided.
     * @param earnedVacationDays the earned vacation days.
     * @return the list of employees, or an empty list if there are none who fit the criteria.
     */
    List<Employee> findByEarnedVacationDaysGreaterThanEqual(int earnedVacationDays);

    /**
     *  gets the age of all employees from the company.
     */
    @Query("select age from Employee")
    List<Integer> getAgeOfAllEmployees();

    @Query("select startedWorkingDate from Employee where username = :username")
    LocalDate getBeginningTimeByUsername(String username);
}
