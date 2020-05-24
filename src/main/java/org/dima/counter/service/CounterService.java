package org.dima.counter.service;

import org.dima.counter.entity.WeeklyHoursList;
import org.dima.counter.entity.payments.WeeklyPayment;
import org.dima.counter.entity.payments.YearlyPayment;

import java.util.List;

public interface CounterService {
    String addTimeSheet(WeeklyHoursList weeklyHoursList);

    String addPaySlip(WeeklyPayment weeklyPayment, WeeklyHoursList weeklyHoursList);

    WeeklyHoursList getTimeSheetByDate(String weekEndingDate, double hourlyRate);

    List<String> getPaySlipsList();

    WeeklyPayment getPaySlipByDate(String weekEndingDate);

    YearlyPayment getYearlyPayments();

    String updateTimeSheet(WeeklyHoursList weeklyHoursList);

    String updatePaySlip(WeeklyPayment weeklyPayment, WeeklyHoursList weeklyHoursList);

    String deleteWeeklyData(String weekEndingDate);
}