package org.dima.counter.buisnessLogic;

import org.dima.counter.entity.DailyReport;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

public class HoursCounter {
    private static double totalWeeklyHours;

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

    public static double calculateTotalHours(DailyReport dailyReport) {
        if (dailyReport.getDay().equals("Saturday")
                || dailyReport.getDay().equals("Sunday") || totalWeeklyHours > 45) {
            return totalWeeklyHours += dailyReport.getHoursDone() * 1.5;
        } else {
            return totalWeeklyHours += dailyReport.getHoursDone();
        }
    }

    public static Date parseDate(String inputDate) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf1.parse(inputDate);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }
}
