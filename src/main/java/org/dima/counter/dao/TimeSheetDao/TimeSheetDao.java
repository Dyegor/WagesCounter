package org.dima.counter.dao.TimeSheetDao;

import org.dima.counter.entity.DailyReport;
import org.dima.counter.entity.WeeklyHoursList;

public interface TimeSheetDao {
    void addTimeSheet(DailyReport dailyReport);

    WeeklyHoursList getTimeSheetByDate(String weekEndingDate);

    void updateTimeSheet(DailyReport dailyReport);

    String deleteTimeSheet(String weekEndingDate);
}
