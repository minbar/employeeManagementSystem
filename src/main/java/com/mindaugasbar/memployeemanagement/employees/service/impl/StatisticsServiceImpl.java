package com.mindaugasbar.memployeemanagement.employees.service.impl;

import com.mindaugasbar.memployeemanagement.employees.dao.EmployeeDao;
import com.mindaugasbar.memployeemanagement.employees.domain.Employee;
import com.mindaugasbar.memployeemanagement.employees.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.OptionalDouble;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private EmployeeDao employeeDao;

    @Override
    public double getAverageEmployeeAge() {
        List<Integer> allEmployeeAges = employeeDao.getAgeOfAllEmployees();
        OptionalDouble averageAge = getAverage(allEmployeeAges);

        if(averageAge.isPresent()){
            return averageAge.getAsDouble();
        }
        return 0;
    }

    private OptionalDouble getAverage(List<Integer> list) {
        return list.stream().mapToDouble(i -> i).average();
    }

    @Override
    public Period getTurnover(long id) {
        LocalDate startedWorkingDate = employeeDao.getBeginningTimeById(id);

        return Period.between(startedWorkingDate, LocalDate.now());
    }



    @Autowired
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
}
