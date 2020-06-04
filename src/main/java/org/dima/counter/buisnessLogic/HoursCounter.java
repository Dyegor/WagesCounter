package org.dima.counter.buisnessLogic;

import org.dima.counter.entity.DailyReport;
import org.dima.counter.entity.WeeklyTimeSheet;

import java.time.LocalTime;

public class HoursCounter {

    public static double calculateAmountOfHours(DailyReport dailyReport) {
        String startTime = dailyReport.getStartTime();
        String finishTime = dailyReport.getFinishTime();

        LocalTime time1 = LocalTime.of(0, 0, 0);
        LocalTime time2 = LocalTime.of(0, 0, 0);

        if ((startTime != null && finishTime != null) && (!startTime.isEmpty() && !finishTime.isEmpty())) {
            time1 = LocalTime.parse(startTime);
            time2 = LocalTime.parse(finishTime);
        }

        long difference = java.time.Duration.between(time1, time2).toMinutes();
        return (double) difference / 60;
    }

    public static double calculateTotalHours(DailyReport dailyReport, WeeklyTimeSheet weeklyTimeSheet) {
        double dailyHours = dailyReport.getHoursDone();
        double totalHours = weeklyTimeSheet.getTotalHours();

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
