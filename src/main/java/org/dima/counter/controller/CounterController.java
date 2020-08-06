package org.dima.counter.controller;

import org.dima.counter.entity.WeeklyTimeSheet;
import org.dima.counter.entity.PaySlip;
import org.dima.counter.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

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

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String successPage() {
        return "success";
    }

    @RequestMapping(value = "/addingWeeklyData", method = RequestMethod.POST)
    public String addingWeeklyData(@ModelAttribute @NotEmpty @Valid WeeklyTimeSheet weeklyTimeSheet
            , @ModelAttribute @NotEmpty @Valid PaySlip weeklyPaySlip) {
        counterService.addTimeSheet(weeklyTimeSheet);
        counterService.addPaySlip(weeklyPaySlip, weeklyTimeSheet);
        return "redirect:/counter/success";
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
    public String getPaySlipByDate(@NotEmpty @RequestParam ("weekEndingDate") String weekEndingDate, Model model) {
        model.addAttribute("paySlip", counterService.getPaySlipByDate(weekEndingDate));
        return "paySlipDetails";
    }

    @RequestMapping(value = "/yearlyReport")
    public String getYearlyReport(Model model) {
        PaySlip yearlyPayslip = counterService.getYearlyPayments();
        model.addAttribute("paymentSummary", yearlyPayslip);
        model.addAttribute("correctPaye", counterService.calculateCorrectPaye(yearlyPayslip.getGrossEarnings()));
        return "annualReport";
    }

    @RequestMapping(value = "/updateWeeklyData/")
    public String updateWeeklyData(@ModelAttribute WeeklyTimeSheet weeklyTimeSheet, @ModelAttribute PaySlip weeklyPaySlip) {
        counterService.updateTimeSheet(weeklyTimeSheet);
        counterService.updatePaySlip(weeklyPaySlip, weeklyTimeSheet);
        return "redirect:/counter/success";
    }

    @RequestMapping(value = "/deleteWeeklyData/{weekEndingDate}")
    public String deleteWeeklyData(@NotEmpty @PathVariable ("weekEndingDate") String weekEndingDate) {
        return counterService.deleteWeeklyData(weekEndingDate);
    }
}