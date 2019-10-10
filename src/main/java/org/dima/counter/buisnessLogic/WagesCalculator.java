package org.dima.counter.buisnessLogic;

public class WagesCalculator {
    public static double calculateGrossEarnings(double normalHours, double overtimeHours) {
        return normalHours * 24 + overtimeHours * 24 * 1.5;
    }

    public static double calculatePaye(double grossEarnings) {
        double yearlyIncome = grossEarnings * 52;
        double paye = yearlyIncome * 0.105 / 52;

        if (yearlyIncome > 13999) {
            paye += (yearlyIncome - 13999) * 0.175 / 52;
        }
        if (yearlyIncome > 47999) {
            paye += (yearlyIncome - 47999) * 0.3 / 52;
        }
        if (yearlyIncome > 69999) {
            paye += (yearlyIncome - 69999) * 0.33 / 52;
        }
        return paye;
    }
}
