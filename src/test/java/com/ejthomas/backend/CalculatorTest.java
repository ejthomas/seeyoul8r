package com.ejthomas.backend;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class CalculatorTest {
    /*
     * Functions to test:
     * - pow()
     */
    @Test
    public void givenPosIntBaseAndExp_whenPow_thenCalculatesPower() {
        // 1st argument is base, 2nd is exponent
        Assertions.assertEquals(125, Calculator.pow(5, 3));
    }

    @Test
    public void givenZeroExpAndPosIntBase_whenPow_thenReturnOne() {
        Assertions.assertEquals(1, Calculator.pow(3, 0));
    }

    @Test
    public void givenZeroBaseAndPosIntExp_whenPow_thenReturnZero() {
        Assertions.assertEquals(0, Calculator.pow(0, 6));
    }

    @Test
    public void givenZeroBaseAndExp_whenPow_thenThrowsArithmeticException() {
        Assertions.assertThrows(ArithmeticException.class, ()->Calculator.pow(0, 0));
    }

    @Test
    public void givenZeroBaseAndNegativeIntExp_whenPow_thenThrowsArithmeticException() {
        Assertions.assertThrows(ArithmeticException.class, ()->Calculator.pow(0, -2));
    }

    @Test
    public void givenPosIntBaseAndNegativeIntExp_whenPow_thenPerformsIntDivide() {
        // This is only correct for integer arithmetic
        Assertions.assertEquals(0, Calculator.pow(2, -1));
        Assertions.assertEquals(1, Calculator.pow(1, -1));
        Assertions.assertEquals(1, Calculator.pow(1, -3));
        Assertions.assertEquals(0, Calculator.pow(3, -2));
    }

    @Test
    public void givenNegativeIntBaseAndPositiveIntExp_whenPow_thenCalculatesPower() {
        Assertions.assertEquals(16, Calculator.pow(-2, 4));
        Assertions.assertEquals(-27, Calculator.pow(-3, 3));
    }
}