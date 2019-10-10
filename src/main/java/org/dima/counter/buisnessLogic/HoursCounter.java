package org.dima.counter.buisnessLogic;

import java.time.LocalTime;

public class HoursCounter {
    private double normalHours;
    private double overtimeHours;

    public static double calculateAmountOfHours(String startTime, String finishTime) {
        LocalTime time1 = LocalTime.of(0, 0, 0);
        LocalTime time2 = LocalTime.of(0, 0, 0);

        if ((startTime != null && finishTime != null) && (!startTime.isEmpty() && !finishTime.isEmpty())) {
            time1 = LocalTime.parse(startTime);
            time2 = LocalTime.parse(finishTime);
        }

        long difference = java.time.Duration.between(time1, time2).toMinutes();
        return (double) difference / 60;
    }

    public HoursCounter calculateOvertimeHours(HoursCounter hoursCounter, double hoursDone, String weekDay) {
        if (weekDay.equals("Saturday")
                || weekDay.equals("Sunday") || hoursCounter.getNormalHours() > 45) {
            hoursCounter.setOvertimeHours(hoursCounter.getOvertimeHours() + hoursDone);
        } else {
            hoursCounter.setNormalHours(hoursCounter.getNormalHours() + hoursDone);
        }
        return hoursCounter;
    }

    public double getNormalHours() {
        return normalHours;
    }

    public void setNormalHours(double normalHours) {
        this.normalHours = normalHours;
    }

    public double getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(double overtimeHours) {
        this.overtimeHours = overtimeHours;
    }
}
