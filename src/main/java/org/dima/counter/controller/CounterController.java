package org.dima.counter.controller;

import org.dima.counter.buisnessLogic.wagesCalculator;
import org.dima.counter.entity.DailyReport;
import org.dima.counter.entity.WeeklyHoursList;
import org.dima.counter.entity.WeeklyPayment;
import org.dima.counter.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
        List<DailyReport> dailyReports = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            dailyReports.add(new DailyReport());
        }
        weeklyHoursList.setDailyReportsList(dailyReports);
        return weeklyHoursList;
    }

    @RequestMapping(value = "/addWeek")
    public String addWeek() {
        return "addingHoursForm";
    }

    @RequestMapping(value = "/addingWeekHours", method = RequestMethod.POST)
    public String addingWeek(@ModelAttribute("weeklyHoursList") WeeklyHoursList weeklyHoursList,
                             @RequestParam("userId") int userId,
                             @RequestParam("weekEndingDate") String weekEndingDate) {
        for (DailyReport dailyReport : weeklyHoursList.getDailyReportsList()) {
            dailyReport.setUserId(userId);
            dailyReport.setWeekEndingDate(weekEndingDate);
            double amountOfHours = wagesCalculator.calculateHours(dailyReport.getStartTime(),
                    dailyReport.getFinishTime());
            dailyReport.setHoursDone(amountOfHours);
            counterService.addWeeklyReport(dailyReport);
        }
        return "success";
    }

    @RequestMapping(value = "/paySlipsList")
    public String showAllPayslips(Model model) {
        model.addAttribute("paySlipsList", counterService.getAllWeeklyHoursLists());
        return "paySlipsList";
    }

    @RequestMapping(value = "/showPaySlip")
    public String showPayslip(@RequestParam("weekEndingDate") String date, Model model) {
        weeklyHoursList = counterService.getWeeklyHoursListByDate(date);
        model.addAttribute("paySlips", weeklyHoursList);
        return "weeklyPaySlip";
    }

    @RequestMapping(value = "/addingWeeklyPayment")
    public String addingPayments(@RequestParam("weekEndingDate") String weekEndingDate) {
        double normalHours = 0;
        double overtimeHours = 0;

        WeeklyPayment weeklyPayment = new WeeklyPayment();

        for (DailyReport dailyReport : weeklyHoursList.getDailyReportsList()) {
            if (dailyReport.getDay().equals("Saturday")
                    || dailyReport.getDay().equals("Sunday") || normalHours > 45) {
                overtimeHours += dailyReport.getHoursDone();
            } else {
                normalHours += dailyReport.getHoursDone();
            }
        }

        weeklyPayment.setWeekEndingDate(weekEndingDate);
        weeklyPayment.setOvertimeHours(overtimeHours);
        weeklyPayment.setNormalHours(normalHours);

        counterService.addWeeklyWages(weeklyPayment);

        return "success";
    }
}