package com.mindaugasbar.memployeemanagement.controller;

import com.mindaugasbar.memployeemanagement.config.GlobalConstants;
import com.mindaugasbar.memployeemanagement.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StatisticsController {

    private StatisticsService statisticsService;

    @RequestMapping("/statistics")
    public String statistics(Model model) {
        model.addAttribute("averageAge", statisticsService.getAverageEmployeeAge());
        model.addAttribute("allEmployeeEarnedVacationDays", statisticsService.getAllCollectedVacationDays());
        model.addAttribute("employeesTooMuchVacation", statisticsService.getEmployeesWhoHaveMoreVacation(GlobalConstants.EMPLOYEES_VACATION_WARNING));
        return "statistics";
    }

    @Autowired
    public void setStatisticsService(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }
}
