package org.dima.counter.buisnessLogic;

import org.dima.counter.entity.DailyReport;

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
}
