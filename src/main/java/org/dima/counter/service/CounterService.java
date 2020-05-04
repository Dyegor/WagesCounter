package org.dima.counter.service;

import org.dima.counter.entity.WeeklyHoursList;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

public interface CounterService {
    String addWeeklyReport(WeeklyHoursList weeklyHoursList, String weekEndingDate) throws ParseException;

    WeeklyHoursList getWeeklyHoursListByDate(Date weekEndingDate);

    List<Date> getPaySlipsList();

    void addWeeklyWages(WeeklyHoursList weeklyHoursList, String weekEndingDate) throws ParseException;

    double getYearlyGrossEarnings(Date currentDate);

    double getYearlyPaye(Date currentDate);
}
