package org.dima.counter.service;

import org.dima.counter.entity.WeeklyTimeSheet;
import org.dima.counter.entity.PaySlip;

import java.util.List;

public interface CounterService {
    boolean checkExistingRecords(WeeklyTimeSheet weeklyTimeSheet);

    WeeklyTimeSheet addTimeSheet(WeeklyTimeSheet weeklyTimeSheet);

    void addPaySlip(WeeklyTimeSheet weeklyTimeSheet);

    WeeklyTimeSheet getTimeSheetByDate(String weekEndingDate, double hourlyRate);

    List<String> getPaySlipsList();

    PaySlip getPaySlipByDate(String weekEndingDate);

    PaySlip getYearlyPayments();

    double calculateCorrectPaye(double totalGrossEarnings);

    void updateTimeSheet(WeeklyTimeSheet weeklyTimeSheet);

    void updatePaySlip(PaySlip weeklyPaySlip, WeeklyTimeSheet weeklyTimeSheet);

    String deleteWeeklyData(String weekEndingDate);
}