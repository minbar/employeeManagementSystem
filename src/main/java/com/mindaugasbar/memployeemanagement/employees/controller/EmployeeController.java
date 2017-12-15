package com.mindaugasbar.memployeemanagement.employees.controller;

import com.mindaugasbar.memployeemanagement.employees.domain.Employee;
import com.mindaugasbar.memployeemanagement.exceptions.MissingEmployeeException;
import com.mindaugasbar.memployeemanagement.employees.service.EmployeeService;
import com.mindaugasbar.memployeemanagement.exceptions.WrongDateFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(path = "/editEmployee/{id}", method = RequestMethod.GET)
    public String editEmployee(@PathVariable(value = "id") long id, Model model) throws MissingEmployeeException {
        Employee employee = employeeService.getEmployeeById(id);
        if(employee == null) {
            final String message = format("the employee with id:[%d] could not be found", id);
            throw new MissingEmployeeException(message);
        }
        model.addAttribute("employee", employee);
        return "editEmployee";
    }

    @RequestMapping(path = "/editEmployee/{id}", method = RequestMethod.POST)
    public String editEmployeePost(@PathVariable(value = "id") long id, @ModelAttribute("employee") Employee employee) {
        try {
            Employee existing = employeeService.getEmployeeById(id);
            employeeService.updateEmployeeNonNullFields(employee);
        } catch (MissingEmployeeException exception) {
            System.out.println(exception.getMessage());
            System.out.println(exception.getStackTrace());
        }
        return "editEmployee";
    }

    @RequestMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) throws MissingEmployeeException {
       Employee employeeToBeDeleted = employeeService.getEmployeeById(id);
       if(employeeToBeDeleted == null) {
           final String message = format("the employee with id:[%d] could not be found", id);
           throw new MissingEmployeeException(message);
       }
       employeeService.deleteEmployee(employeeToBeDeleted.getId());
       return "redirect:/employees";
    }

    @RequestMapping(path = "/addEmployee", method = RequestMethod.GET)
    public String signUp(Model model) {

        model.addAttribute("employee", new Employee());
        return "addEmployee";
    }

    @RequestMapping(path = "/addEmployee", method = RequestMethod.POST)
    public String signUpPost(@ModelAttribute("employee") Employee employee, Model model) {
        employeeService.addEmployee(employee);
        model.addAttribute("message", "the employee was added");
        model.addAttribute("employee", new Employee());
        return "addEmployee";
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
