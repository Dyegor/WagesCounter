package org.dima.counter.entity.payments;

import org.dima.counter.buisnessLogic.WagesCalculator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.List;

@MappedSuperclass
public class PaySlip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String weekEndingDate;
    private double totalHours;
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

    public double getTotalHours() { return totalHours; }

    public void setTotalHours(double totalHours) { this.totalHours = totalHours; }

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
        weeklyPayment.setGrossEarnings(WagesCalculator.calculateGrossEarnings(weeklyPayment));
        weeklyPayment.setPaye(WagesCalculator.calculatePaye(weeklyPayment.getGrossEarnings()));
        weeklyPayment.setAccAmount(WagesCalculator.calculateAcc(weeklyPayment.getGrossEarnings()));
        weeklyPayment.setNetPay(weeklyPayment.getGrossEarnings() - weeklyPayment.getPaye());
    }

    public void populateYearlyPaySlip(PaySlip yearlyPaySlip, List<PaySlip> allWeeklyPayments) {
        for (PaySlip weeklyPaySlip : allWeeklyPayments) {
            yearlyPaySlip.setTotalHours(yearlyPaySlip.getTotalHours() + weeklyPaySlip.getTotalHours());
            yearlyPaySlip.setAccAmount(yearlyPaySlip.getAccAmount() + weeklyPaySlip.getAccAmount());
            yearlyPaySlip.setGrossEarnings(yearlyPaySlip.getGrossEarnings() + weeklyPaySlip.getGrossEarnings());
            yearlyPaySlip.setNetPay(yearlyPaySlip.getNetPay() + weeklyPaySlip.getNetPay());
            yearlyPaySlip.setPaye(yearlyPaySlip.getPaye() + weeklyPaySlip.getPaye());
        }
    }
}
