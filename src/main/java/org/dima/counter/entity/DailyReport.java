package org.dima.counter.entity;

import org.dima.counter.buisnessLogic.HoursCounter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DailyReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String weekEndingDate;
    private String day;
    private String startTime;
    private String finishTime;
    private double hoursDone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }

    public String getWeekEndingDate() {
        return weekEndingDate;
    }

    public void setWeekEndingDate(String weekEndingDate) {
        this.weekEndingDate = weekEndingDate;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public double getHoursDone() {
        return hoursDone;
    }

    public void setHoursDone(double hoursDone) {
        this.hoursDone = hoursDone;
    }

    public DailyReport populateDailyReport(DailyReport dailyReport, WeeklyHoursList weeklyHoursList) {
        dailyReport.setWeekEndingDate(weeklyHoursList.getWeekEndingDate());
        dailyReport.setHoursDone(HoursCounter.calculateAmountOfHours(dailyReport));
        weeklyHoursList.setTotalHours(weeklyHoursList.calculateTotalHours(dailyReport, weeklyHoursList));
        return dailyReport;
    }
}