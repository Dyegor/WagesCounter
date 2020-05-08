package org.dima.counter.entity.payments;

import java.util.List;

public class YearlyPayment extends Payment {

    public YearlyPayment populateYearlyPayment(YearlyPayment yearlyPayment, List<WeeklyPayment> allWeeklyPayments) {
        for (WeeklyPayment weeklyPayment : allWeeklyPayments) {
            yearlyPayment.setTotalHours(yearlyPayment.getTotalHours() + weeklyPayment.getTotalHours());
            yearlyPayment.setAccAmount(yearlyPayment.getAccAmount() + weeklyPayment.getAccAmount());
            yearlyPayment.setGrossEarnings(yearlyPayment.getGrossEarnings() + weeklyPayment.getGrossEarnings());
            yearlyPayment.setNetPay(yearlyPayment.getNetPay() + weeklyPayment.getNetPay());
            yearlyPayment.setPaye(yearlyPayment.getPaye() + weeklyPayment.getPaye());
        }
        return yearlyPayment;
    }
}
