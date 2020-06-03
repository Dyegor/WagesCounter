package org.dima.counter.controller;

import org.dima.counter.entity.WeeklyTimeSheet;
import org.dima.counter.entity.PaySlip;
import org.dima.counter.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/counter")
public class CounterController {

    @Autowired
    private CounterService counterService;

    @ModelAttribute("weeklyTimeSheet")
    public WeeklyTimeSheet populateWeek() {
        return new WeeklyTimeSheet();
    }

    @ModelAttribute("weeklyPaySlip")
    public PaySlip populateWeeklyPaySlip() {
        return new PaySlip();
    }

    @RequestMapping(value = "/addWeek")
    public String addWeek() {
        return "addingHoursForm";
    }

    @RequestMapping(value = "/addingWeeklyData", method = RequestMethod.POST)
    public String addingWeeklyData(@ModelAttribute WeeklyTimeSheet weeklyTimeSheet, @ModelAttribute PaySlip weeklyPaySlip) {
        counterService.addTimeSheet(weeklyTimeSheet);
        return counterService.addPaySlip(weeklyPaySlip, weeklyTimeSheet);
    }

    @RequestMapping(value = "/paySlipsList")
    public String getAllPaySlips(Model model) {
        model.addAttribute("paySlipsList", counterService.getPaySlipsList());
        return "paySlipsList";
    }

    @RequestMapping(value = "/showTimeSheet/{weekEndingDate}")
    public String getTimeSheetByDate(@ModelAttribute PaySlip weeklyPaySlip, Model model) {
        model.addAttribute("timeSheet", counterService.getTimeSheetByDate(weeklyPaySlip.getWeekEndingDate(), weeklyPaySlip.getHourlyRate()));
        return "timeSheetDetails";
    }

    @RequestMapping(value = "/showPaySlip")
    public String getPaySlipByDate(@RequestParam("weekEndingDate") String weekEndingDate, Model model) {
        model.addAttribute("paySlip", counterService.getPaySlipByDate(weekEndingDate));
        return "paySlipDetails";
    }

    @RequestMapping(value = "/yearlyReport")
    public String getYearlyReport(Model model) {
        model.addAttribute("paymentSummary", counterService.getYearlyPayments());
        return "yearlyReport";
    }

    @RequestMapping(value = "/updateWeeklyData/")
    public String updateWeeklyData(@ModelAttribute WeeklyTimeSheet weeklyTimeSheet, @ModelAttribute PaySlip weeklyPaySlip) {
        counterService.updateTimeSheet(weeklyTimeSheet);
        return counterService.updatePaySlip(weeklyPaySlip, weeklyTimeSheet);
    }

    @RequestMapping(value = "/deleteWeeklyData/{weekEndingDate}")
    public String deleteWeeklyData(@PathVariable("weekEndingDate") String weekEndingDate) {
        return counterService.deleteWeeklyData(weekEndingDate);
    }
}