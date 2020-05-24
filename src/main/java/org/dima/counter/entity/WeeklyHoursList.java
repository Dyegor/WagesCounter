package org.dima.counter.entity;

import java.util.ArrayList;
import java.util.List;

public class WeeklyHoursList {

    private List<DailyReport> dailyReportsList;
    private String weekEndingDate;
    private double totalHours;

    public WeeklyHoursList() {
        List<DailyReport> dailyReportList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            dailyReportList.add(new DailyReport());
        }
        setDailyReportsList(dailyReportList);

        getDailyReportsList().get(0).setDay("Monday");
        getDailyReportsList().get(1).setDay("Thursday");
        getDailyReportsList().get(2).setDay("Wednesday");
        getDailyReportsList().get(3).setDay("Thursday");
        getDailyReportsList().get(4).setDay("Friday");
        getDailyReportsList().get(5).setDay("Saturday");
        getDailyReportsList().get(6).setDay("Sunday");
    }

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

    public double calculateTotalHours(DailyReport dailyReport, WeeklyHoursList weeklyHoursList) {
        double dailyHours = dailyReport.getHoursDone();
        double totalHours = weeklyHoursList.getTotalHours();

        if (dailyReport.getDay().equals("Saturday") || dailyReport.getDay().equals("Sunday") || totalHours > 45) {
            totalHours += dailyHours * 1.5;
        } else if ((totalHours + dailyHours) > 45) {
            totalHours = 45 + (totalHours + dailyHours - 45) * 1.5;
        } else {
            totalHours += dailyHours;
        }
        return totalHours;
    }
}
