package com.mindaugasbar.memployeemanagement.employees.service;

import com.mindaugasbar.memployeemanagement.employees.domain.Employee;

import java.time.Period;
import java.util.List;

public interface StatisticsService {

    /**
     * Gets the average age of the companies employees
     */
    double getAverageEmployeeAge();

    /**
     * Gets the period that and employee has been working at the company.
     * @return the period how long an employee has been working at the company.
     */
    Period getTurnover(long id);





}
