package org.dima.counter.entity;

import org.dima.counter.buisnessLogic.WagesCalculator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.List;

@Entity
public class PaySlip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String weekEndingDate;
    private double normalHours;
    private double overTimeHours;
    private double hourlyRate;
    private double grossEarnings;
    private double paye;
    private double accAmount;
    private double netPay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeekEndingDate() {
        return weekEndingDate;
    }

    public void setWeekEndingDate(String weekEndingDate) {
        this.weekEndingDate = weekEndingDate;
    }

    public double getNormalHours() {
        return normalHours;
    }

    public void setNormalHours(double normalHours) {
        this.normalHours = normalHours;
    }

    public double getOverTimeHours() {
        return overTimeHours;
    }

    public void setOverTimeHours(double overTimeHours) {
        this.overTimeHours = overTimeHours;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
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

    public void populateWeek(PaySlip weeklyPayment) {
        double grossEarnings = WagesCalculator.calculateGrossEarnings(weeklyPayment);
        weeklyPayment.setGrossEarnings(grossEarnings);
        weeklyPayment.setPaye(WagesCalculator.calculatePaye(grossEarnings));
        weeklyPayment.setAccAmount(WagesCalculator.calculateAcc(grossEarnings));
        weeklyPayment.setNetPay(grossEarnings - weeklyPayment.getPaye());
    }

    public void populateYearlyPaySlip(PaySlip yearlyPaySlip, List<PaySlip> allWeeklyPayments) {
        for (PaySlip weeklyPaySlip : allWeeklyPayments) {
            yearlyPaySlip.setNormalHours(yearlyPaySlip.getNormalHours() + weeklyPaySlip.getNormalHours());
            yearlyPaySlip.setOverTimeHours(yearlyPaySlip.getOverTimeHours() + weeklyPaySlip.getOverTimeHours());
            yearlyPaySlip.setAccAmount(yearlyPaySlip.getAccAmount() + weeklyPaySlip.getAccAmount());
            yearlyPaySlip.setGrossEarnings(yearlyPaySlip.getGrossEarnings() + weeklyPaySlip.getGrossEarnings());
            yearlyPaySlip.setNetPay(yearlyPaySlip.getNetPay() + weeklyPaySlip.getNetPay());
            yearlyPaySlip.setPaye(yearlyPaySlip.getPaye() + weeklyPaySlip.getPaye());
        }
    }
}
