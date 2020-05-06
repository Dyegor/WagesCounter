package org.dima.counter.service;

import org.dima.counter.entity.WeeklyHoursList;
import org.dima.counter.entity.WeeklyPayment;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

public interface CounterService {
    String addWeeklyReport(WeeklyHoursList weeklyHoursList) throws ParseException;

    WeeklyHoursList getWeeklyHoursListByDate(String weekEndingDate);

    List<Date> getPaySlipsList();

    WeeklyPayment getYearlyPayments();
}
