package org.dima.counter.service;

import org.dima.counter.entity.DailyReport;
import org.dima.counter.entity.WeeklyHoursList;
import org.dima.counter.entity.WeeklyPayment;

import java.util.List;

public interface CounterService {
    void addWeeklyReport(DailyReport dailyReport);

    void deleteReport();

    void updateReport();

    WeeklyHoursList getWeeklyHoursListByDate(String weekEndingDate);

    List<String> getPaySlipsList();

    void addWeeklyWages(WeeklyPayment weeklyPayment);
}
