package org.dima.counter.service;

import org.dima.counter.entity.WeeklyHoursList;
import org.dima.counter.entity.payments.WeeklyPayment;
import org.dima.counter.entity.payments.YearlyPayment;

import java.util.List;

public interface CounterService {
    String addTimeSheet(WeeklyHoursList weeklyHoursList);

    String addPaySlip(WeeklyPayment weeklyPayment, WeeklyHoursList weeklyHoursList);

    List<String> getPaySlipsList();

    WeeklyPayment getPaySlipByDate(String weekEndingDate);

    WeeklyHoursList getTimeSheetByDate(String weekEndingDate);

    YearlyPayment getYearlyPayments();

    String deleteWeeklyData(String weekEndingDate);
}