package org.dima.counter.service;

import org.dima.counter.buisnessLogic.HoursCounter;
import org.dima.counter.buisnessLogic.WagesCalculator;
import org.dima.counter.dao.CounterDao;
import org.dima.counter.entity.DailyReport;
import org.dima.counter.entity.WeeklyHoursList;
import org.dima.counter.entity.WeeklyPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

@Service
public class CounterServiceImpl implements CounterService {

    @Autowired
    private CounterDao counterDao;

    @Override
    public String addWeeklyReport(WeeklyHoursList weeklyHoursList, String weekEndingDate) throws ParseException {
        if (weeklyHoursList != null && weekEndingDate != null) {
            for (DailyReport dailyReport : weeklyHoursList.getDailyReportsList()) {
                dailyReport.setWeekEndingDate(HoursCounter.parseDate(weekEndingDate));
                dailyReport.setHoursDone(HoursCounter.calculateAmountOfHours(dailyReport));
                counterDao.addWeeklyReport(dailyReport);
            }
            return "success";
        } else {
            return "incorrectInput";
        }
    }

    @Override
    public WeeklyHoursList getWeeklyHoursListByDate(Date weekEndingDate) {
        return counterDao.getWeeklyHoursListByDate(weekEndingDate);
    }

    @Override
    public List<Date> getPaySlipsList() {
        return counterDao.getPaySlipsList();
    }

    @Override
    public void addWeeklyWages(WeeklyHoursList weeklyHoursList, String weekEndingDate) throws ParseException {
        double grossEarnings;
        double paye;
        WeeklyPayment weeklyPayment = new WeeklyPayment();

        weeklyPayment.setTotalHours(weeklyHoursList.getTotalHours());
        grossEarnings = WagesCalculator.calculateGrossEarnings(weeklyHoursList.getTotalHours());
        paye = WagesCalculator.calculatePaye(grossEarnings);
        weeklyPayment.setWeekEndingDate(HoursCounter.parseDate(weekEndingDate));
        weeklyPayment.setGrossEarnings(grossEarnings);
        weeklyPayment.setPaye(paye);
        weeklyPayment.setAccAmount(WagesCalculator.calculateAcc(grossEarnings));
        weeklyPayment.setNetPay(grossEarnings - paye);

        counterDao.addWeeklyWages(weeklyPayment);
    }

    @Override
    public double getYearlyGrossEarnings(Date currentDate) {
        return counterDao.getYearlyGrossEarnings(currentDate);
    }

    @Override
    public double getYearlyPaye(Date currentDate) {
        return counterDao.getYearlyPaye(currentDate);
    }
}
