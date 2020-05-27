package org.dima.counter.service;

import org.dima.counter.entity.WeeklyHoursList;
import org.dima.counter.entity.payments.WeeklyPaySlip;
import org.dima.counter.entity.payments.YearlyPaySlip;

import java.util.List;

public interface CounterService {
    String addTimeSheet(WeeklyHoursList weeklyHoursList);

    String addPaySlip(WeeklyPaySlip weeklyPaySlip, WeeklyHoursList weeklyHoursList);

    WeeklyHoursList getTimeSheetByDate(String weekEndingDate, double hourlyRate);

    List<String> getPaySlipsList();

    WeeklyPaySlip getPaySlipByDate(String weekEndingDate);

    YearlyPaySlip getYearlyPayments();

    String updateTimeSheet(WeeklyHoursList weeklyHoursList);

    String updatePaySlip(WeeklyPaySlip weeklyPaySlip, WeeklyHoursList weeklyHoursList);

    String deleteWeeklyData(String weekEndingDate);
}