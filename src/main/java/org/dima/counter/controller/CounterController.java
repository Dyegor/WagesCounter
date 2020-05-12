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

    @RequestMapping(value = "/addingWeekHours", method = RequestMethod.POST)
    public String addingWeek(@ModelAttribute WeeklyHoursList weeklyHoursList){
        return counterService.addWeeklyReport(weeklyHoursList);
    }

    @RequestMapping(value = "/addingWeeklyPayment/{weekEndingDate}", method = RequestMethod.POST)
    public String addingWeeklyPayment(@PathVariable("weekEndingDate") String weekEndingDate,
                                      @ModelAttribute("weeklyPayment") WeeklyPayment weeklyPayment,
                                      @ModelAttribute WeeklyHoursList weeklyHoursListCreated){
        weeklyHoursListCreated.setWeekEndingDate(weekEndingDate);
        return counterService.addWeeklyPayment(weeklyPayment, weeklyHoursListCreated);
    }

    @RequestMapping(value = "/paySlipsList")
    public String showAllPayslips(Model model) {
        model.addAttribute("paySlipsList", counterService.getPaySlipsList());
        return "weeklyReportsList";
    }

    @RequestMapping(value = "/showPaySlip")
    public String showPayslipByDate(@RequestParam("weekEndingDate") String weekEndingDate, Model model) {
        model.addAttribute("paySlips", counterService.getWeeklyHoursListByDate(weekEndingDate));
        return "weeklyPaySlip";
    }

    @RequestMapping(value = "/yearlyReport")
    public String getYearlyReport(Model model) {
        model.addAttribute("paymentSummary", counterService.getYearlyPayments());
        return "yearlyReport";
    }

    @RequestMapping(value = "/deleteWeeklyReport/{weekEndingDate}")
    public String deleteTimeSheet(@PathVariable("weekEndingDate") String weekEndingDate) {
        return counterService.deleteTimeSheet(weekEndingDate);
    }
}