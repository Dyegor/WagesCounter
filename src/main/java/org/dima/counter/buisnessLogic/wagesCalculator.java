package org.dima.counter.buisnessLogic;

import java.time.LocalTime;

public class wagesCalculator {
    public static double calculateHours(String startTime, String finishTime) {
        LocalTime time1 = LocalTime.of(0,0,0);
        LocalTime time2 = LocalTime.of(0,0,0);

        if ((startTime != null && finishTime != null) && (!startTime.isEmpty() && !finishTime.isEmpty())) {
            time1 = LocalTime.parse(startTime);
            time2 = LocalTime.parse(finishTime);
        }

        long difference = java.time.Duration.between(time1, time2).toMinutes();
        return (double) difference / 60;
    }

    public static double calculateWages(double normalHours, double overtimeHours) {
        return normalHours * 24 + overtimeHours * 24 * 1.5;
    }
}
