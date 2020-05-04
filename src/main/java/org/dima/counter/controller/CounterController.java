package org.dima.counter.controller;

import org.dima.counter.entity.DailyReport;
import org.dima.counter.entity.WeeklyHoursList;
import org.dima.counter.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/counter")
public class CounterController {

    @Autowired
    private CounterService counterService;
    private WeeklyHoursList weeklyHoursList;

    @ModelAttribute("weeklyHoursList")
    public WeeklyHoursList populateWeek() {
        WeeklyHoursList weeklyHoursList = new WeeklyHoursList();
        return weeklyHoursList;
    }

    @RequestMapping(value = "/addWeek")
    public String addWeek() {
        return "addingHoursForm";
    }

    @RequestMapping(value = "/addingWeekHours", method = RequestMethod.POST)
    public String addingWeek(@ModelAttribute("weeklyHoursList") WeeklyHoursList weeklyHoursList,
                             @RequestParam("weekEndingDate") String weekEndingDate) throws ParseException {
        return counterService.addWeeklyReport(weeklyHoursList, weekEndingDate);
    }

    @RequestMapping(value = "/paySlipsList")
    public String showAllPayslips(Model model) {
        model.addAttribute("paySlipsList", counterService.getPaySlipsList());
        return "paySlipsList";
    }

    @RequestMapping(value = "/showPaySlip")
    public String showPayslip(@RequestParam("weekEndingDate") Date date, Model model) {
        weeklyHoursList = counterService.getWeeklyHoursListByDate(date);
        model.addAttribute("paySlips", weeklyHoursList);
        return "weeklyPaySlip";
    }

    @RequestMapping(value = "/addingWeeklyPayment")
    public String addingPayments(@ModelAttribute("weeklyHoursList") WeeklyHoursList weeklyHoursList,
                                 @RequestParam("weekEndingDate") String weekEndingDate) throws ParseException {
        counterService.addWeeklyWages(weeklyHoursList, weekEndingDate);
        return "success";
    }
}