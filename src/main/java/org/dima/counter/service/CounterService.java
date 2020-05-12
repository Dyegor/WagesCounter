package org.dima.counter.service;

import org.dima.counter.entity.WeeklyHoursList;
import org.dima.counter.entity.payments.WeeklyPayment;
import org.dima.counter.entity.payments.YearlyPayment;

import java.sql.Date;
import java.util.List;

public interface CounterService {
    String addWeeklyReport(WeeklyHoursList weeklyHoursList);

    String addWeeklyPayment(WeeklyPayment weeklyPayment, WeeklyHoursList weeklyHoursList);

    WeeklyHoursList getWeeklyHoursListByDate(String weekEndingDate);

    List<String> getPaySlipsList();

    YearlyPayment getYearlyPayments();

    String deleteTimeSheet(String weekEndingDate);
}
