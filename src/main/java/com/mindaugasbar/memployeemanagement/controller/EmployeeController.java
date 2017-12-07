package com.mindaugasbar.memployeemanagement.controller;

import com.mindaugasbar.memployeemanagement.domain.Employee;
import com.mindaugasbar.memployeemanagement.exceptions.MissingEmployeeException;
import com.mindaugasbar.memployeemanagement.service.EmployeeService;
import com.mindaugasbar.memployeemanagement.service.validators.EmployeeDataValidator;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

import static java.lang.String.format;

@Controller
public class EmployeeController {


    private EmployeeService employeeService;
    private EmployeeDataValidator employeeDataValidator;

    @RequestMapping(path = "/employees", method = RequestMethod.GET)
    public String employees(Model model) {
        List<Employee> employees = employeeService.getEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }

    @RequestMapping(path = "/editEmployee/{username}", method = RequestMethod.GET)
    public String editEmployee(@PathVariable(value = "username") String username, Model model) throws MissingEmployeeException {
        Employee employee = employeeService.getEmployeeByUsername(username);
        if(employee == null) {
            final String message = format("the employee with username:[%s] could not be found", username);
            throw new MissingEmployeeException(message);
        }
        model.addAttribute("employee", employee);
        return "editEmployee";
    }

    @RequestMapping(path = "/editEmployee/{username}", method = RequestMethod.POST)
    public String editEmployeePost(@PathVariable(value = "username") String username, @ModelAttribute("employee") Employee employee) {
        try {
            Employee existring = employeeService.getEmployeeByUsername(username);
            employeeService.updateEmployeeNonNullFields(employee);
        } catch (MissingEmployeeException exception) {
            System.out.println(exception.getMessage());
            System.out.println(exception.getStackTrace());
        }
        return "editEmployee";
    }

    @RequestMapping("/deleteEmployee/{username}")
    public String deleteEmployee(@PathVariable(value = "username") String username) throws MissingEmployeeException {
       Employee employeeToBeDeleted = employeeService.getEmployeeByUsername(username);
       if(employeeToBeDeleted == null) {
           final String message = format("the employee with username:[%s] could not be found", username);
           throw new MissingEmployeeException(message);
       }
       employeeService.deleteEmployee(employeeToBeDeleted.getId());
       return "employees";
    }

    @RequestMapping(path = "/addEmployee", method = RequestMethod.GET)
    public String signUp(Model model) {
        Employee employee = new Employee();
        model.addAttribute(employee);
        return "addEmployee";
    }

    @RequestMapping(path = "/addEmployee", method = RequestMethod.POST)
    public String signUpPost(@ModelAttribute("employee") Employee employee, Model model) {
        employeeService.addEmployee(employee);
        model.addAttribute("employee", new Employee());
        return "addEmployee";
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setEmployeeDataValidator(EmployeeDataValidator employeeDataValidator) {
        this.employeeDataValidator = employeeDataValidator;
    }
}
