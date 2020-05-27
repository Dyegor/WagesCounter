package org.dima.counter.entity.payments;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class PaySlip {

    private String weekEndingDate;
    private double totalHours;
    private double hourlyRate;
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
}
