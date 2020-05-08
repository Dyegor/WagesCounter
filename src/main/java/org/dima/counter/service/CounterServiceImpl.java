package org.dima.counter.service;

import org.dima.counter.buisnessLogic.HoursCounter;
import org.dima.counter.dao.CounterDao;
import org.dima.counter.entity.DailyReport;
import org.dima.counter.entity.WeeklyHoursList;
import org.dima.counter.entity.payments.WeeklyPayment;
import org.dima.counter.entity.payments.YearlyPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class CounterServiceImpl implements CounterService {

    @Autowired
    private CounterDao counterDao;

    @Override
    public String addWeeklyReport(WeeklyHoursList weeklyHoursList) {
        if (weeklyHoursList != null && weeklyHoursList.getWeekEndingDate() != null) {
            WeeklyPayment weeklyPayment = new WeeklyPayment();
            for (DailyReport dailyReport : weeklyHoursList.getDailyReportsList()) {
                dailyReport.setWeekEndingDate(weeklyHoursList.getWeekEndingDate());
                dailyReport.setHoursDone(HoursCounter.calculateAmountOfHours(dailyReport));
                weeklyPayment.setTotalHours(weeklyPayment.calculateTotalHours(dailyReport, weeklyPayment));
                counterDao.addWeeklyReport(dailyReport);
            }

            weeklyPayment.setWeekEndingDate(weeklyHoursList.getWeekEndingDate());
            weeklyPayment.populateWeek(weeklyPayment);
            counterDao.addWeeklyWages(weeklyPayment);
            return "success";
        } else {
            return "incorrectInput";
        }
    }

    @Override
    public List<Date> getPaySlipsList() {
        return counterDao.getPaySlipsList();
    }

    @Override
    public WeeklyHoursList getWeeklyHoursListByDate(String weekEndingDate) {
        return counterDao.getWeeklyHoursListByDate(weekEndingDate);
    }

    @Override
    public YearlyPayment getYearlyPayments() {
        List<WeeklyPayment> allWeeklyPayments = counterDao.getWeeklyPaymentsList();
        YearlyPayment paymentSummary = new YearlyPayment();
        paymentSummary.populateYearlyPayment(paymentSummary, allWeeklyPayments);
        return paymentSummary;
    }
}
