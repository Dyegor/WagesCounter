package org.dima.counter.service;

import org.dima.counter.dao.PaySlipDao.PaySlipDao;
import org.dima.counter.dao.TimeSheetDao.TimeSheetDao;
import org.dima.counter.entity.DailyReport;
import org.dima.counter.entity.WeeklyHoursList;
import org.dima.counter.entity.payments.WeeklyPaySlip;
import org.dima.counter.entity.payments.YearlyPaySlip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounterServiceImpl implements CounterService {

    @Autowired
    private PaySlipDao paySlipDao;
    @Autowired
    private TimeSheetDao timeSheetDao;

    @Override
    public String addTimeSheet(WeeklyHoursList weeklyHoursList) {
        for (DailyReport dailyReport : weeklyHoursList.getDailyReportsList()) {
            dailyReport.populateDailyReport(dailyReport, weeklyHoursList);
            timeSheetDao.addTimeSheet(dailyReport);
        }
        return "success";
    }

    @Override
    public String addPaySlip(WeeklyPaySlip weeklyPaySlip, WeeklyHoursList weeklyHoursList) {
        weeklyPaySlip.setTotalHours(weeklyHoursList.getTotalHours());
        weeklyPaySlip.populateWeek(weeklyPaySlip);
        paySlipDao.addPaySlip(weeklyPaySlip);
        return "success";
    }

    @Override
    public WeeklyHoursList getTimeSheetByDate(String weekEndingDate, double hourlyRate) {
        WeeklyHoursList weeklyHoursList = timeSheetDao.getTimeSheetByDate(weekEndingDate);
        for (DailyReport dailyReport : weeklyHoursList.getDailyReportsList()) {
            weeklyHoursList.setTotalHours(weeklyHoursList.calculateTotalHours(dailyReport, weeklyHoursList));
        }
        weeklyHoursList.setHourlyRate(hourlyRate);
        weeklyHoursList.setWeekEndingDate(weekEndingDate);
        return weeklyHoursList;
    }

    @Override
    public List<String> getPaySlipsList() {
        return paySlipDao.getPaySlipsList();
    }

    @Override
    public WeeklyPaySlip getPaySlipByDate(String weekEndingDate) {
        return paySlipDao.getPaySlipByDate(weekEndingDate);
    }

    @Override
    public YearlyPaySlip getYearlyPayments() {
        List<WeeklyPaySlip> allWeeklyPayments = paySlipDao.getAllWeeklyPayments();
        YearlyPaySlip paymentSummary = new YearlyPaySlip();
        paymentSummary.populateYearlyPaySlip(paymentSummary, allWeeklyPayments);
        return paymentSummary;
    }

    @Override
    public String updateTimeSheet(WeeklyHoursList weeklyHoursList) {
        for (DailyReport dailyReport : weeklyHoursList.getDailyReportsList()) {
            dailyReport.populateDailyReport(dailyReport, weeklyHoursList);
            timeSheetDao.updateTimeSheet(dailyReport);
        }
        return "success";
    }

    @Override
    public String updatePaySlip(WeeklyPaySlip weeklyPaySlip, WeeklyHoursList weeklyHoursList) {
        weeklyPaySlip.setTotalHours(weeklyHoursList.getTotalHours());
        weeklyPaySlip.populateWeek(weeklyPaySlip);
        paySlipDao.updatePaySlip(weeklyPaySlip);
        return "success";
    }

    @Override
    public String deleteWeeklyData(String weekEndingDate) {
        paySlipDao.deletePaySlip(weekEndingDate);
        return timeSheetDao.deleteTimeSheet(weekEndingDate);
    }
}