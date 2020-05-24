package org.dima.counter.dao.PaySlipDao;

import org.dima.counter.entity.DailyReport;
import org.dima.counter.entity.WeeklyHoursList;
import org.dima.counter.entity.payments.WeeklyPayment;

import java.util.List;

public interface PaySlipDao {
    void addPaySlip(WeeklyPayment weeklyPayment);

    List<String> getPaySlipsList();

    WeeklyPayment getPaySlipByDate(String weekEndingDate);

    List<WeeklyPayment> getAllWeeklyPayments();

    void updatePaySlip(WeeklyPayment weeklyPayment);

    String deletePaySlip(String weekEndingDate);
}
