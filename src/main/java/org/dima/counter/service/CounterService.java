package org.dima.counter.service;

import org.dima.counter.entity.WeeklyTimeSheet;
import org.dima.counter.entity.PaySlip;

import java.util.List;

public interface CounterService {
    void addTimeSheet(WeeklyTimeSheet weeklyTimeSheet);

    String addPaySlip(PaySlip weeklyPaySlip, WeeklyTimeSheet weeklyTimeSheet);

    WeeklyTimeSheet getTimeSheetByDate(String weekEndingDate, double hourlyRate);

    List<String> getPaySlipsList();

    PaySlip getPaySlipByDate(String weekEndingDate);

    PaySlip getYearlyPayments();

    void updateTimeSheet(WeeklyTimeSheet weeklyTimeSheet);

    String updatePaySlip(PaySlip weeklyPaySlip, WeeklyTimeSheet weeklyTimeSheet);

    String deleteWeeklyData(String weekEndingDate);
}