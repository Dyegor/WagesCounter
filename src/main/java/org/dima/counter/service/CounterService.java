package org.dima.counter.service;

import org.dima.counter.entity.DailyReport;
import org.dima.counter.entity.WeeklyHoursList;
import org.dima.counter.entity.WeeklyPayment;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

public interface CounterService {
    void addWeeklyReport(WeeklyHoursList weeklyHoursList, String weekEndingDate) throws ParseException;

    WeeklyHoursList getWeeklyHoursListByDate(Date weekEndingDate);

    List<Date> getPaySlipsList();

    void addWeeklyWages(WeeklyPayment weeklyPayment);

    double getYearlyGrossEarnings(int userId, Date currentDate);

    double getYearlyPaye(int userId, Date currentDate);
}
