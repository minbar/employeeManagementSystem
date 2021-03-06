package com.mindaugasbar.memployeemanagement.authorization.controller;

import com.mindaugasbar.memployeemanagement.authorization.domain.Role;
import com.mindaugasbar.memployeemanagement.authorization.domain.User;
import com.mindaugasbar.memployeemanagement.authorization.service.RoleService;
import com.mindaugasbar.memployeemanagement.authorization.service.SecurityService;
import com.mindaugasbar.memployeemanagement.authorization.service.UserService;
import com.mindaugasbar.memployeemanagement.authorization.validator.UserValidator;
import com.mindaugasbar.memployeemanagement.employees.domain.Employee;
import com.mindaugasbar.memployeemanagement.employees.service.EmployeeService;
import com.mindaugasbar.memployeemanagement.exceptions.MissingUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Objects;

@Controller
public class UserController {

    private UserService userService;
    private SecurityService securityService;
    private UserValidator userValidator;
    private RoleService roleService;
    private EmployeeService employeeService;

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public String users(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @RequestMapping(path = "/editUser/{id}", method = RequestMethod.GET)
    public String editUser(@PathVariable(value = "id") long id, Model model) {
        User user = userService.findById(id);
        List<Employee> employeesWithoutUser = employeeService.getEmployeesWithNoAccount();
        List<String> roleNames = roleService.getAllRoleNames();
        model.addAttribute("user", user);
        model.addAttribute("employeesWithoutAccount", employeesWithoutUser);
        model.addAttribute("roleNames", roleNames);

        return "editUser";
    }

    @RequestMapping(path = "/editUser/{id}", method = RequestMethod.POST)
    public String editUserPost(@PathVariable(value = "id") long id, @ModelAttribute(value = "roleName") String roleName,
                               @ModelAttribute("employeeId") Integer employeeId, @ModelAttribute(value = "user") User user) throws MissingUserException {

        if(employeeId == null){
            user.setEmployee(null);
        } else {
            Employee newEmployee = employeeService.getEmployeeById(employeeId);
            Objects.requireNonNull(newEmployee);
            user.setEmployee(newEmployee);
        }

        Role newRole = roleService.findByName(roleName);
        Objects.requireNonNull(newRole);
        user.setRole(newRole);

        userService.updateUser(user);
        return "editUser";
    }

    @RequestMapping(path = "deleteUser/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable(value = "id") long id) throws MissingUserException {
        userService.deleteById(id);
        return "redirect:/users";
    }

    @RequestMapping(path= "/addUser", method = RequestMethod.GET)
    public String registration(Model model) {

        model.addAttribute("user", new User());
        model.addAttribute("employeesWithoutAccount", employeeService.getEmployeesWithNoAccount());
        model.addAttribute("roleNames", roleService.getAllRoleNames());

        return "addUser";
    }

    @RequestMapping(path = "/addUser", method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") User user, @ModelAttribute("roleName") String roleName, @ModelAttribute("employeeId") Integer employeeId,
                               BindingResult bindingResult, Model model) {
        Objects.requireNonNull(roleName);
        Objects.requireNonNull(user);
        Employee relatedEmployee = employeeService.getEmployeeById(employeeId);
        if(relatedEmployee != null) {
            user.setEmployee(relatedEmployee);
        }
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "addUser";
        }

        userService.save(user, roleName);

        securityService.autologin(user.getUsername(), user.getPasswordConfirm());

        return "redirect:/addUser";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Autowired
    public void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
}
