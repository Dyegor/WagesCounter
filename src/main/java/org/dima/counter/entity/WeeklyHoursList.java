package org.dima.counter.entity;

import org.dima.counter.entity.payments.WeeklyPayment;

import java.util.List;

public class WeeklyHoursList {

    private List<DailyReport> dailyReportsList;
    private  String weekEndingDate;
    private  double totalHours;

    public List<DailyReport> getDailyReportsList() {
        return dailyReportsList;
    }

    public void setDailyReportsList(List<DailyReport> dailyReportsList) {
        this.dailyReportsList = dailyReportsList;
    }

    public String getWeekEndingDate() {
        return weekEndingDate;
    }

    public void setWeekEndingDate(String weekEndingDate) {
        this.weekEndingDate = weekEndingDate;
    }

    public double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }
}
