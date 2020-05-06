package org.dima.counter.entity;

import org.dima.counter.buisnessLogic.WagesCalculator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WeeklyPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String weekEndingDate;
    private double totalHours;
    private double grossEarnings;
    private double paye;
    private double accAmount;
    private double netPay;

    public String getWeekEndingDate() {
        return weekEndingDate;
    }

    public void setWeekEndingDate(String weekEndingDate) {
        this.weekEndingDate = weekEndingDate;
    }

    public double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }

    public double getGrossEarnings() {
        return grossEarnings;
    }

    public void setGrossEarnings(double grossEarnings) {
        this.grossEarnings = grossEarnings;
    }

    public double getPaye() {
        return paye;
    }

    public void setPaye(double paye) {
        this.paye = paye;
    }

    public double getAccAmount() {
        return accAmount;
    }

    public void setAccAmount(double accAmount) {
        this.accAmount = accAmount;
    }

    public double getNetPay() {
        return netPay;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }

    public double calculateTotalHours(DailyReport dailyReport) {
        double dailyHours = dailyReport.getHoursDone();
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

    public WeeklyPayment populateWeek(WeeklyPayment weeklyPayment) {
        grossEarnings = WagesCalculator.calculateGrossEarnings(weeklyPayment.getTotalHours());
        paye = WagesCalculator.calculatePaye(grossEarnings);

        weeklyPayment.setGrossEarnings(grossEarnings);
        weeklyPayment.setPaye(paye);
        weeklyPayment.setAccAmount(WagesCalculator.calculateAcc(grossEarnings));
        weeklyPayment.setNetPay(grossEarnings - paye);
        return weeklyPayment;
    }
}