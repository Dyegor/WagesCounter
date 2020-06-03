package org.dima.counter.dao;

import org.dima.counter.entity.DailyReport;
import org.dima.counter.entity.WeeklyTimeSheet;
import org.dima.counter.entity.PaySlip;

import java.util.List;

public interface CounterDao {
    void addTimeSheet(DailyReport dailyReport);

    WeeklyTimeSheet getTimeSheetByDate(String weekEndingDate);

    void updateTimeSheet(DailyReport dailyReport);

    String deleteTimeSheet(String weekEndingDate);

    void addPaySlip(PaySlip weeklyPaySlip);

    List<String> getPaySlipsList();

    PaySlip getPaySlipByDate(String weekEndingDate);

    List<PaySlip> getAllWeeklyPayments();

    void updatePaySlip(PaySlip weeklyPaySlip);

    String deletePaySlip(String weekEndingDate);
}
