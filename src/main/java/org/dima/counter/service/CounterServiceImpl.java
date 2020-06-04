package org.dima.counter.service;

import org.dima.counter.buisnessLogic.HoursCounter;
import org.dima.counter.dao.CounterDao;
import org.dima.counter.entity.DailyReport;
import org.dima.counter.entity.WeeklyTimeSheet;
import org.dima.counter.entity.PaySlip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounterServiceImpl implements CounterService {

    @Autowired
    private CounterDao counterDao;

    @Override
    public void addTimeSheet(WeeklyTimeSheet weeklyTimeSheet) {
        for (DailyReport dailyReport : weeklyTimeSheet.getDailyReportsList()) {
            dailyReport.populateDailyReport(dailyReport, weeklyTimeSheet);
            counterDao.addTimeSheet(dailyReport);
        }
    }

    @Override
    public String addPaySlip(PaySlip weeklyPaySlip, WeeklyTimeSheet weeklyTimeSheet) {
        weeklyPaySlip.setTotalHours(weeklyTimeSheet.getTotalHours());
        weeklyPaySlip.populateWeek(weeklyPaySlip);
        counterDao.addPaySlip(weeklyPaySlip);
        return "success";
    }

    @Override
    public WeeklyTimeSheet getTimeSheetByDate(String weekEndingDate, double hourlyRate) {
        WeeklyTimeSheet weeklyTimeSheet = counterDao.getTimeSheetByDate(weekEndingDate);
        for (DailyReport dailyReport : weeklyTimeSheet.getDailyReportsList()) {
            weeklyTimeSheet.setTotalHours(HoursCounter.calculateTotalHours(dailyReport, weeklyTimeSheet));
        }
        weeklyTimeSheet.setHourlyRate(hourlyRate);
        weeklyTimeSheet.setWeekEndingDate(weekEndingDate);
        return weeklyTimeSheet;
    }

    @Override
    public List<String> getPaySlipsList() {
        return counterDao.getPaySlipsList();
    }

    @Override
    public PaySlip getPaySlipByDate(String weekEndingDate) {
        return counterDao.getPaySlipByDate(weekEndingDate);
    }

    @Override
    public PaySlip getYearlyPayments() {
        List<PaySlip> allWeeklyPayments = counterDao.getAllWeeklyPayments();
        PaySlip paymentSummary = new PaySlip();
        paymentSummary.populateYearlyPaySlip(paymentSummary, allWeeklyPayments);
        return paymentSummary;
    }

    @Override
    public void updateTimeSheet(WeeklyTimeSheet weeklyTimeSheet) {
        for (DailyReport dailyReport : weeklyTimeSheet.getDailyReportsList()) {
            dailyReport.populateDailyReport(dailyReport, weeklyTimeSheet);
            counterDao.updateTimeSheet(dailyReport);
        }
    }

    @Override
    public String updatePaySlip(PaySlip weeklyPaySlip, WeeklyTimeSheet weeklyTimeSheet) {
        weeklyPaySlip.setTotalHours(weeklyTimeSheet.getTotalHours());
        weeklyPaySlip.populateWeek(weeklyPaySlip);
        counterDao.updatePaySlip(weeklyPaySlip);
        return "success";
    }

    @Override
    public String deleteWeeklyData(String weekEndingDate) {
        counterDao.deletePaySlip(weekEndingDate);
        return counterDao.deleteTimeSheet(weekEndingDate);
    }
}