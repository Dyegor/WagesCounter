package org.dima.counter.dao.PaySlipDao;

import org.dima.counter.entity.payments.WeeklyPayment;

import java.util.List;

public interface PaySlipDao {
    void addPaySlip(WeeklyPayment weeklyPayment);

    List<String> getPaySlipsList();

    WeeklyPayment getPaySlipByDate(String weekEndingDate);

    List<WeeklyPayment> getAllWeeklyPayments();

    String deletePaySlip(String weekEndingDate);
}
