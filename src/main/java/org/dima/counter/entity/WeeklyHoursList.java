package org.dima.counter.entity;

import java.util.List;

// Wrapper class for Daily Reports List
public class WeeklyHoursList {
    private List<DailyReport> dailyReportsList;

    public List<DailyReport> getDailyReportsList() {
        return dailyReportsList;
    }

    public void setDailyReportsList(List<DailyReport> dailyReportsList) {
        this.dailyReportsList = dailyReportsList;
    }
}
