package org.dima.counter.service;

import org.dima.counter.dao.PaySlipDao.PaySlipDao;
import org.dima.counter.dao.TimeSheetDao.TimeSheetDao;
import org.dima.counter.entity.DailyReport;
import org.dima.counter.entity.WeeklyTimeSheet;
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
    public void addTimeSheet(WeeklyTimeSheet weeklyTimeSheet) {
        for (DailyReport dailyReport : weeklyTimeSheet.getDailyReportsList()) {
            dailyReport.populateDailyReport(dailyReport, weeklyTimeSheet);
            timeSheetDao.addTimeSheet(dailyReport);
        }
    }

    @Override
    public String addPaySlip(WeeklyPaySlip weeklyPaySlip, WeeklyTimeSheet weeklyTimeSheet) {
        weeklyPaySlip.setTotalHours(weeklyTimeSheet.getTotalHours());
        weeklyPaySlip.populateWeek(weeklyPaySlip);
        paySlipDao.addPaySlip(weeklyPaySlip);
        return "success";
    }

    @Override
    public WeeklyTimeSheet getTimeSheetByDate(String weekEndingDate, double hourlyRate) {
        WeeklyTimeSheet weeklyTimeSheet = timeSheetDao.getTimeSheetByDate(weekEndingDate);
        for (DailyReport dailyReport : weeklyTimeSheet.getDailyReportsList()) {
            weeklyTimeSheet.setTotalHours(weeklyTimeSheet.calculateTotalHours(dailyReport, weeklyTimeSheet));
        }
        weeklyTimeSheet.setHourlyRate(hourlyRate);
        weeklyTimeSheet.setWeekEndingDate(weekEndingDate);
        return weeklyTimeSheet;
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
    public void updateTimeSheet(WeeklyTimeSheet weeklyTimeSheet) {
        for (DailyReport dailyReport : weeklyTimeSheet.getDailyReportsList()) {
            dailyReport.populateDailyReport(dailyReport, weeklyTimeSheet);
            timeSheetDao.updateTimeSheet(dailyReport);
        }
    }

    @Override
    public String updatePaySlip(WeeklyPaySlip weeklyPaySlip, WeeklyTimeSheet weeklyTimeSheet) {
        weeklyPaySlip.setTotalHours(weeklyTimeSheet.getTotalHours());
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