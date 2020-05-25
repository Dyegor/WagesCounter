package org.dima.counter.entity.payments;

import org.dima.counter.buisnessLogic.WagesCalculator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WeeklyPayment extends Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public WeeklyPayment populateWeek(WeeklyPayment weeklyPayment) {
        weeklyPayment.setGrossEarnings(WagesCalculator.calculateGrossEarnings(weeklyPayment));
        weeklyPayment.setPaye(WagesCalculator.calculatePaye(weeklyPayment.getGrossEarnings()));
        weeklyPayment.setAccAmount(WagesCalculator.calculateAcc(weeklyPayment.getGrossEarnings()));
        weeklyPayment.setNetPay(weeklyPayment.getGrossEarnings() - weeklyPayment.getPaye());
        return weeklyPayment;
    }
}