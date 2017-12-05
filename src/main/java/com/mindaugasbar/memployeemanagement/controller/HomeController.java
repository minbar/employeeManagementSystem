package com.mindaugasbar.memployeemanagement.controller;

import com.mindaugasbar.memployeemanagement.domain.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/statistics")
    public String statistics() {
        return "statistics";
    }

    @RequestMapping(name = "/signup", method = RequestMethod.GET)
    public String signUp(Model model) {
        Employee employee = new Employee();
        model.addAttribute(employee);
        return "signup";
    }

    @RequestMapping(name = "/signup", method = RequestMethod.POST)
    public String signUpPost(@ModelAttribute("employee") Employee employee, Model model) {
        return "signup";
    }
}
