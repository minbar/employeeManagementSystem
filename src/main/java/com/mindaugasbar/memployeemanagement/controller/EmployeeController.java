package com.mindaugasbar.memployeemanagement.controller;

import com.mindaugasbar.memployeemanagement.domain.Employee;
import com.mindaugasbar.memployeemanagement.exceptions.MissingEmployeeException;
import com.mindaugasbar.memployeemanagement.service.EmployeeService;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.websocket.server.PathParam;
import java.util.List;

import static java.lang.String.format;

@Controller
public class EmployeeController {


    private EmployeeService employeeService;

    @RequestMapping(path = "/employees", method = RequestMethod.GET)
    public String employees(Model model) {
        List<Employee> employees = employeeService.getEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }

    @RequestMapping("/editEmployee/{username}")
    public String editEmployee(@PathVariable(value = "username") String username, Model model) throws MissingEmployeeException {
        Employee employee = employeeService.getEmployeeByUsername(username);
        if(employee == null) {
            final String message = format("the employee with username:[%s] could not be found", username);
            throw new MissingEmployeeException(message);
        }
        model.addAttribute("employee", employee);
        return "editEmployee";
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
