package org.dima.counter.entity;

import java.util.ArrayList;
import java.util.List;

public class WeeklyTimeSheet {

    private List<DailyReport> dailyReportsList;
    private String weekEndingDate;
    private double normalHours;
    private double overTimeHours;
    private double hourlyRate;

    public WeeklyTimeSheet() {
        List<DailyReport> dailyReportList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            dailyReportList.add(new DailyReport());
        }
        setDailyReportsList(dailyReportList);

        getDailyReportsList().get(0).setDay("Monday");
        getDailyReportsList().get(1).setDay("Tuesday");
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

    public double getNormalHours() {
        return normalHours;
    }

    public void setNormalHours(double normalHours) {
        this.normalHours = normalHours;
    }

    public double getOverTimeHours() {
        return overTimeHours;
    }

    public void setOverTimeHours(double overTimeHours) {
        this.overTimeHours = overTimeHours;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}

