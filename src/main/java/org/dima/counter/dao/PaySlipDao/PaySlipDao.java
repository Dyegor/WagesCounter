package org.dima.counter.dao.PaySlipDao;

import org.dima.counter.entity.payments.PaySlip;

import java.util.List;

public interface PaySlipDao {
    void addPaySlip(PaySlip weeklyPaySlip);

    List<String> getPaySlipsList();

    PaySlip getPaySlipByDate(String weekEndingDate);

    List<PaySlip> getAllWeeklyPayments();

    void updatePaySlip(PaySlip weeklyPaySlip);

    String deletePaySlip(String weekEndingDate);
}
