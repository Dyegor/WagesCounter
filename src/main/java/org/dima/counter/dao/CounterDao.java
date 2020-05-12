package org.dima.counter.dao;

import org.dima.counter.entity.DailyReport;
import org.dima.counter.entity.WeeklyHoursList;
import org.dima.counter.entity.payments.WeeklyPayment;

import java.util.List;

public interface CounterDao {
    void addWeeklyReport(DailyReport dailyReport);

    void addWeeklyWages(WeeklyPayment weeklyPayment);

    List<String> getPaySlipsList();

    WeeklyHoursList getWeeklyHoursListByDate(String weekEndingDate);

    List<WeeklyPayment> getWeeklyPaymentsList();

    String deleteTimeSheet(String weekEndingDate);
}