package org.dima.counter.dao;

import org.dima.counter.entity.DailyReport;
import org.dima.counter.entity.WeeklyHoursList;
import org.dima.counter.entity.payments.WeeklyPayment;

import java.util.List;

public interface CounterDao {
    void addWeeklyReport(DailyReport dailyReport);

    void addWeeklyWages(WeeklyPayment weeklyPayment);

    List<String> getPaySlipsList();

    WeeklyPayment getPaySlipByDate(String weekEndingDate);

    WeeklyHoursList getWeeklyHoursListByDate(String weekEndingDate);

    List<WeeklyPayment> getAllWeeklyPayments();

    String deleteTimeSheet(String weekEndingDate);
}