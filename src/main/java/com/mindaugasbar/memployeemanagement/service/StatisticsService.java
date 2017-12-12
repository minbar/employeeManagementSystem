package com.mindaugasbar.memployeemanagement.service;

import com.mindaugasbar.memployeemanagement.domain.Employee;

import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface StatisticsService {

    /**
     * Gets all of the vacation days that have been acquired by the companies employees.
     * @return the number of days that the employees can take for vacation.
     */
    int getAllCollectedVacationDays();

    /**
     * Gets all of the employees that have more vacation days to take than the number provided.
     * @param vacationDays the number of vacation days.
     * @return a list of employees that meet the specified criteria
     */
    List<Employee> getEmployeesWhoHaveMoreVacation(int vacationDays);

    /**
     * Gets the average age of the companies employees
     */
    double getAverageEmployeeAge();

    /**
     * Gets the period that and employee has been working at the company.
     * @return the period how long an employee has been working at the company.
     */
    Period getTurnover(String username);





}
