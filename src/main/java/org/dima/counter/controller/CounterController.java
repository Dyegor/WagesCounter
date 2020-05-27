package org.dima.counter.controller;

import org.dima.counter.entity.WeeklyHoursList;
import org.dima.counter.entity.payments.WeeklyPaySlip;
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

    @ModelAttribute("weeklyHoursList")
    public WeeklyHoursList populateWeek() {
        return new WeeklyHoursList();
    }

    @ModelAttribute("weeklyPaySlip")
    public WeeklyPaySlip populateWeeklyPaySlip() {
        return new WeeklyPaySlip();
    }

    @RequestMapping(value = "/addWeek")
    public String addWeek() {
        return "addingHoursForm";
    }

    @RequestMapping(value = "/addingWeeklyData", method = RequestMethod.POST)
    public String addingWeeklyData(@ModelAttribute WeeklyHoursList weeklyHoursList, @ModelAttribute WeeklyPaySlip weeklyPaySlip) {
        counterService.addTimeSheet(weeklyHoursList);
        return counterService.addPaySlip(weeklyPaySlip, weeklyHoursList);
    }

    @RequestMapping(value = "/paySlipsList")
    public String getAllPaySlips(Model model) {
        model.addAttribute("paySlipsList", counterService.getPaySlipsList());
        return "paySlipsList";
    }

    @RequestMapping(value = "/showTimeSheet/{weekEndingDate}")
    public String getTimeSheetByDate(@ModelAttribute WeeklyPaySlip weeklyPaySlip, Model model) {
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
    public String updateWeeklyData(@ModelAttribute WeeklyHoursList weeklyHoursList, @ModelAttribute WeeklyPaySlip weeklyPaySlip) {
        counterService.updateTimeSheet(weeklyHoursList);
        return counterService.updatePaySlip(weeklyPaySlip, weeklyHoursList);
    }

    @RequestMapping(value = "/deleteWeeklyData/{weekEndingDate}")
    public String deleteWeeklyData(@PathVariable("weekEndingDate") String weekEndingDate) {
        return counterService.deleteWeeklyData(weekEndingDate);
    }
}