package org.dima.counter.buisnessLogic;

import org.dima.counter.entity.DailyReport;
import org.dima.counter.entity.WeeklyTimeSheet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HoursCounterTest {

    private static DailyReport dailyReport;
    private static WeeklyTimeSheet weeklyTimeSheet;

    @BeforeAll
    static void setUp() {
        dailyReport = new DailyReport();
        dailyReport.setStartTime("10:30");
        dailyReport.setFinishTime("22:30");
        dailyReport.setHoursDone(5);
        dailyReport.setDay("Saturday");

        weeklyTimeSheet = new WeeklyTimeSheet();
        weeklyTimeSheet.setTotalHours(40);
    }

    @Test
    void calculateAmountOfHours() {
        assertEquals(12, HoursCounter.calculateAmountOfHours(dailyReport), "Returns calculated amount of hours");
    }

    @Test
    void calculateTotalHours() {
        assertEquals(47.5, HoursCounter.calculateTotalHours(dailyReport, weeklyTimeSheet), "Returns calculated total hours");
    }
}