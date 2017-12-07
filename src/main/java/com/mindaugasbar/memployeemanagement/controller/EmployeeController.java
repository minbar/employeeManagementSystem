package com.mindaugasbar.memployeemanagement.controller;

import com.mindaugasbar.memployeemanagement.domain.Employee;
import com.mindaugasbar.memployeemanagement.service.EmployeeService;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class EmployeeController {


    EmployeeService employeeService;

    @RequestMapping("/employees")
    public String employees(Model model) {
        List<Employee> employees = employeeService.getEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }

    @InjectService
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
