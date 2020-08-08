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
        double amountOfHours = (double) difference / 60;

        if (amountOfHours >= 6.5) {
            amountOfHours -= 0.5;
        }

        return amountOfHours;
    }

    public static void calculateHours(DailyReport dailyReport, WeeklyTimeSheet weeklyTimeSheet) {
        double dailyHours = dailyReport.getHoursDone();
        double normalHours = weeklyTimeSheet.getNormalHours();
        double overTimeHours = weeklyTimeSheet.getOverTimeHours();

        if (dailyReport.getDay().equals("Saturday") || dailyReport.getDay().equals("Sunday") || normalHours >= 45) {
            overTimeHours += dailyHours;
        } else if ((normalHours + dailyHours) > 45) {
            overTimeHours = normalHours + dailyHours - 45;
            normalHours = 45;
        } else {
            normalHours += dailyHours;
        }
        weeklyTimeSheet.setNormalHours(normalHours);
        weeklyTimeSheet.setOverTimeHours(overTimeHours);
    }
}
