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
        dailyReport.setDay("Saturday");

        weeklyTimeSheet = new WeeklyTimeSheet();
    }

    @Test
    void calculateAmountOfHours() {
        assertEquals(11.5, HoursCounter.calculateAmountOfHours(dailyReport), "Returns calculated amount of hours");
    }

    @Test
    void calculateHours() {
        dailyReport.setHoursDone(HoursCounter.calculateAmountOfHours(dailyReport));
        HoursCounter.calculateHours(dailyReport, weeklyTimeSheet);
        assertEquals(0, weeklyTimeSheet.getNormalHours(), "Returns calculated normal hours");
        assertEquals(11.5, weeklyTimeSheet.getOverTimeHours(), "Returns calculated overtime hours");
    }
}