package org.dima.counter.service;

import org.dima.counter.entity.DailyReport;
import org.dima.counter.entity.WeeklyHoursList;

public interface CounterService {
    void addWeeklyReport(DailyReport dailyReport);

    void deleteReport();

    void updateReport();

    WeeklyHoursList getWeeklyHoursListByDate(String weekEndingDate);
}
