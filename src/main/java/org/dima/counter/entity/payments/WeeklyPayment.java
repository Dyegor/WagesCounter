package org.dima.counter.entity.payments;

import org.dima.counter.buisnessLogic.WagesCalculator;
import org.dima.counter.entity.DailyReport;
import org.dima.counter.entity.WeeklyHoursList;

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
        weeklyPayment.setGrossEarnings(WagesCalculator.calculateGrossEarnings(weeklyPayment.getTotalHours()));
        weeklyPayment.setPaye(WagesCalculator.calculatePaye(weeklyPayment.getGrossEarnings()));
        weeklyPayment.setAccAmount(WagesCalculator.calculateAcc(weeklyPayment.getGrossEarnings()));
        weeklyPayment.setNetPay(weeklyPayment.getGrossEarnings() - weeklyPayment.getPaye());
        return weeklyPayment;
    }

    public double calculateTotalHours(DailyReport dailyReport, WeeklyHoursList weeklyHoursList) {
        double dailyHours = dailyReport.getHoursDone();
        double totalHours = weeklyHoursList.getTotalHours();

        if (dailyReport.getDay().equals("Saturday") || dailyReport.getDay().equals("Sunday")
                || totalHours > 45) {
            totalHours += dailyHours * 1.5;
        } else if ((totalHours + dailyHours) > 45) {
            totalHours = 45 + (totalHours + dailyHours - 45) * 1.5;
        } else {
            totalHours += dailyHours;
        }
        return totalHours;
    }
}