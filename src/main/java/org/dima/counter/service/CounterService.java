package org.dima.counter.service;

import org.dima.counter.entity.WeeklyTimeSheet;
import org.dima.counter.entity.payments.WeeklyPaySlip;
import org.dima.counter.entity.payments.YearlyPaySlip;

import java.util.List;

public interface CounterService {
    void addTimeSheet(WeeklyTimeSheet weeklyTimeSheet);

    String addPaySlip(WeeklyPaySlip weeklyPaySlip, WeeklyTimeSheet weeklyTimeSheet);

    WeeklyTimeSheet getTimeSheetByDate(String weekEndingDate, double hourlyRate);

    List<String> getPaySlipsList();

    WeeklyPaySlip getPaySlipByDate(String weekEndingDate);

    YearlyPaySlip getYearlyPayments();

    void updateTimeSheet(WeeklyTimeSheet weeklyTimeSheet);

    String updatePaySlip(WeeklyPaySlip weeklyPaySlip, WeeklyTimeSheet weeklyTimeSheet);

    String deleteWeeklyData(String weekEndingDate);
}