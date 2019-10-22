package org.dima.counter.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class DailyReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int userId;
    private Date weekEndingDate;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getWeekEndingDate() {
        return weekEndingDate;
    }

    public void setWeekEndingDate(Date weekEndingDate) {
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
}
