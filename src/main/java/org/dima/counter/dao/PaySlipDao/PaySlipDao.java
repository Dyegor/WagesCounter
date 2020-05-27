package org.dima.counter.dao.PaySlipDao;

import org.dima.counter.entity.payments.WeeklyPaySlip;

import java.util.List;

public interface PaySlipDao {
    void addPaySlip(WeeklyPaySlip weeklyPayment);

    List<String> getPaySlipsList();

    WeeklyPaySlip getPaySlipByDate(String weekEndingDate);

    List<WeeklyPaySlip> getAllWeeklyPayments();

    void updatePaySlip(WeeklyPaySlip weeklyPaySlip);

    String deletePaySlip(String weekEndingDate);
}
