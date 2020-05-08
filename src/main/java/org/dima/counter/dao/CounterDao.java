package org.dima.counter.dao;

import org.dima.counter.entity.DailyReport;
import org.dima.counter.entity.WeeklyHoursList;
import org.dima.counter.entity.payments.WeeklyPayment;

import java.sql.Date;
import java.util.List;

public interface CounterDao {
    void addWeeklyReport(DailyReport dailyReport);

    void addWeeklyWages(WeeklyPayment weeklyPayment);

    List<Date> getPaySlipsList();

    WeeklyHoursList getWeeklyHoursListByDate(String weekEndingDate);

    List<WeeklyPayment> getWeeklyPaymentsList();
}