package org.dima.counter.controller;

import org.dima.counter.entity.WeeklyHoursList;
import org.dima.counter.entity.payments.WeeklyPayment;
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

    @ModelAttribute("weeklyPayment")
    public WeeklyPayment populateWeeklyPayment() {
        return new WeeklyPayment();
    }

    @RequestMapping(value = "/addWeek")
    public String addWeek() {
        return "addingHoursForm";
    }

    @RequestMapping(value = "/addingWeeklyData", method = RequestMethod.POST)
    public String addingWeeklyData(@ModelAttribute WeeklyHoursList weeklyHoursList, @ModelAttribute WeeklyPayment weeklyPayment){
        counterService.addTimeSheet(weeklyHoursList);
        return counterService.addPaySlip(weeklyPayment, weeklyHoursList);
    }

    @RequestMapping(value = "/paySlipsList")
    public String getAllPaySlips(Model model) {
        model.addAttribute("paySlipsList", counterService.getPaySlipsList());
        return "paySlipsList";
    }

    @RequestMapping(value = "/showTimeSheet/{weekEndingDate}")
    public String getTimeSheetByDate(@ModelAttribute WeeklyPayment weeklyPayment, Model model) {
        model.addAttribute("timeSheet", counterService.getTimeSheetByDate(weeklyPayment.getWeekEndingDate(), weeklyPayment.getHourlyRate()));
        return "timeSheetDetails";
    }

    @RequestMapping(value = "/showPaySlip")
    public String getPaySlipByDate(@RequestParam("weekEndingDate") String weekEndingDate, Model model) {
        model.addAttribute("paySlip", counterService.getPaySlipByDate(weekEndingDate));
        return "paySlip";
    }

    @RequestMapping(value = "/yearlyReport")
    public String getYearlyReport(Model model) {
        model.addAttribute("paymentSummary", counterService.getYearlyPayments());
        return "yearlyReport";
    }

    @RequestMapping(value = "/deleteWeeklyData/{weekEndingDate}")
    public String deleteWeeklyData(@PathVariable("weekEndingDate") String weekEndingDate) {
        return counterService.deleteWeeklyData(weekEndingDate);
    }
}