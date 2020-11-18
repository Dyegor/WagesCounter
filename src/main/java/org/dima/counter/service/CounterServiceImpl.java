package org.dima.counter.service;

import org.dima.counter.buisnessLogic.HoursCounter;
import org.dima.counter.buisnessLogic.WagesCalculator;
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
    public boolean checkExistingRecords(WeeklyTimeSheet weeklyTimeSheet) {
        if (!counterDao.checkExistingRecords(weeklyTimeSheet, "DailyReport")
                || !counterDao.checkExistingRecords(weeklyTimeSheet, "PaySlip")) {
            return false;
        }
        return true;
    }

    @Override
    public WeeklyTimeSheet addTimeSheet(WeeklyTimeSheet weeklyTimeSheet) {
        for (DailyReport dailyReport : weeklyTimeSheet.getDailyReportsList()) {
            dailyReport.populateDailyReport(dailyReport, weeklyTimeSheet);
            counterDao.addTimeSheet(dailyReport);
        }
        return weeklyTimeSheet;
    }

    @Override
    public void addPaySlip(WeeklyTimeSheet weeklyTimeSheet) {
        PaySlip weeklyPaySlip = new PaySlip();
        weeklyPaySlip.setWeekEndingDate(weeklyTimeSheet.getWeekEndingDate());
        weeklyPaySlip.setHourlyRate(weeklyTimeSheet.getHourlyRate());
        weeklyPaySlip.setNormalHours(weeklyTimeSheet.getNormalHours());
        weeklyPaySlip.setOverTimeHours(weeklyTimeSheet.getOverTimeHours());
        weeklyPaySlip.populateWeek(weeklyPaySlip);
        counterDao.addPaySlip(weeklyPaySlip);
    }

    @Override
    public WeeklyTimeSheet getTimeSheetByDate(String weekEndingDate, double hourlyRate) {
        WeeklyTimeSheet weeklyTimeSheet = counterDao.getTimeSheetByDate(weekEndingDate);
        for (DailyReport dailyReport : weeklyTimeSheet.getDailyReportsList()) {
            HoursCounter.calculateHours(dailyReport, weeklyTimeSheet);
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
    public double calculateCorrectPaye(double totalGrossEarnings) {
        return WagesCalculator.calculatePaye(totalGrossEarnings / 52);
    }

    @Override
    public void updateTimeSheet(WeeklyTimeSheet weeklyTimeSheet) {
        for (DailyReport dailyReport : weeklyTimeSheet.getDailyReportsList()) {
            dailyReport.populateDailyReport(dailyReport, weeklyTimeSheet);
            counterDao.updateTimeSheet(dailyReport);
        }
    }

    @Override
    public void updatePaySlip(PaySlip weeklyPaySlip, WeeklyTimeSheet weeklyTimeSheet) {
        weeklyPaySlip.setNormalHours(weeklyTimeSheet.getNormalHours());
        weeklyPaySlip.setOverTimeHours(weeklyTimeSheet.getOverTimeHours());
        weeklyPaySlip.populateWeek(weeklyPaySlip);
        counterDao.updatePaySlip(weeklyPaySlip);
    }

    @Override
    public String deleteWeeklyData(String weekEndingDate) {
        counterDao.deletePaySlip(weekEndingDate);
        return counterDao.deleteTimeSheet(weekEndingDate);
    }
}