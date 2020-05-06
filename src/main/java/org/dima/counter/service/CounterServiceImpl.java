package org.dima.counter.service;

import org.dima.counter.buisnessLogic.HoursCounter;
import org.dima.counter.dao.CounterDao;
import org.dima.counter.entity.DailyReport;
import org.dima.counter.entity.WeeklyHoursList;
import org.dima.counter.entity.WeeklyPayment;
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

                weeklyPayment.calculateTotalHours(dailyReport);
                weeklyPayment.setWeekEndingDate(weeklyHoursList.getWeekEndingDate());
                counterDao.addWeeklyReport(dailyReport);
            }

            weeklyPayment.populateWeek(weeklyPayment);
            counterDao.addWeeklyWages(weeklyPayment);
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
    public double getYearlyGrossEarnings(Date currentDate) {
        return counterDao.getYearlyGrossEarnings(currentDate);
    }

    @Override
    public double getYearlyPaye(Date currentDate) {
        return counterDao.getYearlyPaye(currentDate);
    }
}
