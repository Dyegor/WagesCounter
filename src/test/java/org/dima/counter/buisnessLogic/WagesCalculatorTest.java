package org.dima.counter.buisnessLogic;

import org.dima.counter.entity.PaySlip;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WagesCalculatorTest {

    private static PaySlip weeklyPayment;

    @BeforeAll
    static void setUp() {
        weeklyPayment = new PaySlip();
        weeklyPayment.setNormalHours(123);
        weeklyPayment.setOverTimeHours(456);
        weeklyPayment.setHourlyRate(789);
    }

    @Test
    void calculateGrossEarnings() {
        assertEquals(636723, WagesCalculator.calculateGrossEarnings(weeklyPayment), "Should return ACC amount");
    }

    @Test
    void calculatePaye() {
        assertEquals(765.8825961538462, WagesCalculator.calculatePaye(2850), "Should return calculated Paye");
    }

    @Test
    void calculateAcc() {
        assertEquals(1716049.3671, WagesCalculator.calculateAcc(123456789), "Should return ACC amount");
    }
}