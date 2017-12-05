package com.mindaugasbar.memployeemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/signup")
    public String signUp() {
        return "signup";
    }
}
