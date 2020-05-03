package org.dima.counter.service;

import org.dima.counter.entity.DailyReport;
import org.dima.counter.entity.WeeklyHoursList;
import org.dima.counter.entity.WeeklyPayment;

import java.sql.Date;
import java.util.List;

public interface CounterService {
    void addWeeklyReport(DailyReport dailyReport);

    WeeklyHoursList getWeeklyHoursListByDate(Date weekEndingDate);

    List<Date> getPaySlipsList();

    void addWeeklyWages(WeeklyPayment weeklyPayment);

    double getYearlyGrossEarnings(int userId, Date currentDate);

    double getYearlyPaye(int userId, Date currentDate);
}
