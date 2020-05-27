package org.dima.counter.entity.payments;

import java.util.List;

public class YearlyPaySlip extends PaySlip {

    public void populateYearlyPaySlip(YearlyPaySlip yearlyPaySlip, List<WeeklyPaySlip> allWeeklyPayments) {
        for (WeeklyPaySlip weeklyPaySlip : allWeeklyPayments) {
            yearlyPaySlip.setTotalHours(yearlyPaySlip.getTotalHours() + weeklyPaySlip.getTotalHours());
            yearlyPaySlip.setAccAmount(yearlyPaySlip.getAccAmount() + weeklyPaySlip.getAccAmount());
            yearlyPaySlip.setGrossEarnings(yearlyPaySlip.getGrossEarnings() + weeklyPaySlip.getGrossEarnings());
            yearlyPaySlip.setNetPay(yearlyPaySlip.getNetPay() + weeklyPaySlip.getNetPay());
            yearlyPaySlip.setPaye(yearlyPaySlip.getPaye() + weeklyPaySlip.getPaye());
        }
    }
}
