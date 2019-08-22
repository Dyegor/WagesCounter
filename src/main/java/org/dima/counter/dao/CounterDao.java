package org.dima.counter.dao;

import org.dima.counter.entity.DailyReport;
import org.dima.counter.entity.WeeklyHoursList;

public interface CounterDao {
    void addWeeklyReport(DailyReport dailyReport);

    void deleteReport();

    void updateReport();

    WeeklyHoursList getWeeklyHoursListByDate(String weekEndingDate);
}