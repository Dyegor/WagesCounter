package org.dima.counter.buisnessLogic;

public class WagesCalculator {
    public static double calculateGrossEarnings(double normalHours, double overtimeHours) {
        return normalHours * 22.5 + overtimeHours * 22.5 * 1.5;
    }

    public static double calculatePaye(double grossEarnings) {
        final double yearlyIncome = grossEarnings * 52;
        final double lowIncomePaye = 13999;
        final double mediumIncomePaye = 34000;
        final double highIncomePaye = 22000;
        double totalPaye = 0;
        
        if (yearlyIncome > 69999) {
            totalPaye = ((grossEarnings - 70000) * 0.33 + lowIncomePaye * 0.105 + mediumIncomePaye * 0.175
                    + highIncomePaye * 0.3) / 52;
        } else if (yearlyIncome > 47999) {
            totalPaye = ((yearlyIncome - 48000) * 0.30 + lowIncomePaye * 0.105 + mediumIncomePaye * 0.175) / 52;
        } else if (yearlyIncome > 13999) {
            totalPaye = ((yearlyIncome - 14000) * 0.175 + lowIncomePaye * 0.105) / 52;
        }
        return totalPaye;
    }

    public static double calculateAcc(double grossEarnings) {
        return grossEarnings * 0.0139;
    }
}
