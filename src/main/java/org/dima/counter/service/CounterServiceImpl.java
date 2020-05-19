package org.dima.counter.service;

import org.dima.counter.dao.PaySlipDao.PaySlipDao;
import org.dima.counter.dao.TimeSheetDao.TimeSheetDao;
import org.dima.counter.entity.DailyReport;
import org.dima.counter.entity.WeeklyHoursList;
import org.dima.counter.entity.payments.WeeklyPayment;
import org.dima.counter.entity.payments.YearlyPayment;
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
    public String addPaySlip(WeeklyPayment weeklyPayment, WeeklyHoursList weeklyHoursList) {
        weeklyPayment.setTotalHours(weeklyHoursList.getTotalHours());
        weeklyPayment.populateWeek(weeklyPayment);
        paySlipDao.addPaySlip(weeklyPayment);
        return "success";
    }

    @Override
    public List<String> getPaySlipsList() {
        return paySlipDao.getPaySlipsList();
    }

    @Override
    public WeeklyPayment getPaySlipByDate(String weekEndingDate) {
        return paySlipDao.getPaySlipByDate(weekEndingDate);
    }

    @Override
    public WeeklyHoursList getTimeSheetByDate(String weekEndingDate) {
        WeeklyHoursList weeklyHoursList = timeSheetDao.getTimeSheetByDate(weekEndingDate);
        for (DailyReport dailyReport : weeklyHoursList.getDailyReportsList()) {
            weeklyHoursList.setTotalHours(weeklyHoursList.calculateTotalHours(dailyReport, weeklyHoursList));
        }
        weeklyHoursList.setWeekEndingDate(weekEndingDate);
        return weeklyHoursList;
    }

    @Override
    public YearlyPayment getYearlyPayments() {
        List<WeeklyPayment> allWeeklyPayments = paySlipDao.getAllWeeklyPayments();
        YearlyPayment paymentSummary = new YearlyPayment();
        paymentSummary.populateYearlyPayment(paymentSummary, allWeeklyPayments);
        return paymentSummary;
    }

    @Override
    public String deleteWeeklyData(String weekEndingDate) {
        paySlipDao.deletePaySlip(weekEndingDate);
        return timeSheetDao.deleteTimeSheet(weekEndingDate);
    }
}