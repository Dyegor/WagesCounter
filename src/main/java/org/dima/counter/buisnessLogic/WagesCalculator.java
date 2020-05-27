package org.dima.counter.buisnessLogic;

import org.dima.counter.entity.payments.WeeklyPaySlip;

public class WagesCalculator {

    public static double calculateGrossEarnings(WeeklyPaySlip weeklyPaySlip) {
        return weeklyPaySlip.getTotalHours() * weeklyPaySlip.getHourlyRate();
    }

    public static double calculatePaye(double grossEarnings) {
        final double yearlyIncome = grossEarnings * 52;
        final double lowIncomePaye = 13999 * 0.105;
        final double mediumIncomePaye = 34000 * 0.175;
        final double highIncomePaye = 22000 * 0.3;
        double totalPaye = 0;
        
        if (yearlyIncome > 69999) {
            totalPaye = ((yearlyIncome - 70000) * 0.33 + lowIncomePaye + mediumIncomePaye + highIncomePaye) / 52;
        } else if (yearlyIncome > 47999) {
            totalPaye = ((yearlyIncome - 48000) * 0.30 + lowIncomePaye + mediumIncomePaye) / 52;
        } else if (yearlyIncome > 13999) {
            totalPaye = ((yearlyIncome - 14000) * 0.175 + lowIncomePaye) / 52;
        } else {
            totalPaye = yearlyIncome * 0.105;
        }
        return totalPaye;
    }

    public static double calculateAcc(double grossEarnings) {
        return grossEarnings * 0.0139;
    }
}
