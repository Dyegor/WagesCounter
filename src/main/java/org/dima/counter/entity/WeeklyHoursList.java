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


    public double calculateTotalHours(DailyReport dailyReport, WeeklyHoursList weeklyHoursList) {
        double dailyHours = dailyReport.getHoursDone();
        double totalHours = weeklyHoursList.getTotalHours();

        if (dailyReport.getDay().equals("Saturday") || dailyReport.getDay().equals("Sunday")
                || totalHours > 45) {
            totalHours += dailyHours * 1.5;
        } else if ((totalHours + dailyHours) > 45) {
            totalHours = 45 + (totalHours + dailyHours - 45) * 1.5;
        } else {
            totalHours += dailyHours;
        }
        return totalHours;
    }
}
