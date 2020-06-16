package org.dima.counter.buisnessLogic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WagesCalculatorTest {

    @Test
    void calculateGrossEarnings() {
        assertEquals(70077626, WagesCalculator.calculateGrossEarnings(1234, 56789), "Should return ACC amount");
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