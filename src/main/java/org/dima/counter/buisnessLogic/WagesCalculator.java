package org.dima.counter.buisnessLogic;

public class WagesCalculator {
    public static double calculateGrossEarnings(double normalHours, double overtimeHours) {
        return normalHours * 24 + overtimeHours * 24 * 1.5;
    }
}
