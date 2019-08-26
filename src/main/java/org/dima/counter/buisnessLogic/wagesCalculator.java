package org.dima.counter.buisnessLogic;

import java.time.LocalTime;

public class wagesCalculator {
    public static double calculateHours(String startTime, String finishTime) {
        LocalTime time1 = LocalTime.parse(startTime);
        LocalTime time2 = LocalTime.parse(finishTime);
        long difference = java.time.Duration.between(time1, time2).toMinutes();
        return (double) difference / 60;
    }
}
