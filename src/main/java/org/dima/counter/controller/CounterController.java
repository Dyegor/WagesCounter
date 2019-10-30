package org.dima.counter.controller;

import org.dima.counter.buisnessLogic.HoursCounter;
import org.dima.counter.buisnessLogic.WagesCalculator;
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

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
                             @RequestParam("weekEndingDate") String weekEndingDate) throws ParseException {
        for (DailyReport dailyReport : weeklyHoursList.getDailyReportsList()) {
            dailyReport.setUserId(userId);
            dailyReport.setWeekEndingDate(HoursCounter.parseDate(weekEndingDate));
            dailyReport.setHoursDone(HoursCounter.calculateAmountOfHours(dailyReport.getStartTime(),
                    dailyReport.getFinishTime()));
            counterService.addWeeklyReport(dailyReport);
        }
        return "success";
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
    public String addingPayments(@RequestParam("userId") String userId,
                                 @RequestParam("weekEndingDate") String weekEndingDate) throws ParseException {
        double grossEarnings;
        double paye;
        HoursCounter hoursCounter = new HoursCounter();
        WeeklyPayment weeklyPayment = new WeeklyPayment();

        for (DailyReport dailyReport : weeklyHoursList.getDailyReportsList()) {
            hoursCounter.calculateOvertimeHours(hoursCounter, dailyReport.getHoursDone(), dailyReport.getDay());
        }

        grossEarnings = WagesCalculator.calculateGrossEarnings(hoursCounter.getNormalHours(), hoursCounter.getOvertimeHours());
        paye = WagesCalculator.calculatePaye(grossEarnings);

        weeklyPayment.setUserId(Integer.parseInt(userId));
        weeklyPayment.setWeekEndingDate(HoursCounter.parseDate(weekEndingDate));
        weeklyPayment.setNormalHours(hoursCounter.getNormalHours());
        weeklyPayment.setOvertimeHours(hoursCounter.getOvertimeHours());
        weeklyPayment.setGrossEarnings(grossEarnings);
        weeklyPayment.setPaye(paye);
        weeklyPayment.setAccAmount(WagesCalculator.calculateAcc(grossEarnings));
        weeklyPayment.setNetPay(grossEarnings - paye);

        counterService.addWeeklyWages(weeklyPayment);
        return "success";
    }

    @RequestMapping(value = "/showYearlyReport")
    public String showYearlyReport(Model model) {
        int userId = 3;
        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        model.addAttribute("yearlyGrossEarnings", counterService.getYearlyGrossEarnings(userId, sqlDate));
        model.addAttribute("yearlyPaye", counterService.getYearlyPaye(userId, sqlDate));
        model.addAttribute("correctPaye", WagesCalculator.calculatePaye(counterService.getYearlyPaye(userId, sqlDate)));
        return "yearlyReport";
    }
}