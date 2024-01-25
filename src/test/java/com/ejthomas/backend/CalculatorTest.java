package com.ejthomas.backend;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class CalculatorTest {
    
    /*
     * pow
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
    public void givenPosIntBaseAndNegativeIntExp_whenPow_thenPerformsDivide() {
        Assertions.assertEquals(0.5, Calculator.pow(2, -1));
        Assertions.assertEquals(1, Calculator.pow(1, -1));
        Assertions.assertEquals(1, Calculator.pow(1, -3));
        Assertions.assertEquals(1.0/9.0, Calculator.pow(3, -2));
    }

    @Test
    public void givenNegativeIntBaseAndPositiveIntExp_whenPow_thenCalculatesPower() {
        Assertions.assertEquals(16, Calculator.pow(-2, 4));
        Assertions.assertEquals(-27, Calculator.pow(-3, 3));
    }

    @Test
    public void givenPositiveBaseAndNonIntExp_whenPow_thenCalculatesPower() {
        Assertions.assertTrue(Calculator.abs(2.82842712475 - Calculator.pow(8, 0.5)) < Calculator.EPSILON_REL * 2.82842712475);
        Assertions.assertTrue(Calculator.abs(36912.0142177 - Calculator.pow(13, 4.1)) < Calculator.EPSILON_REL * 36912.0142177);
    }

    @Test
    public void givenNegativeBaseAndNonIntExp_whenPow_thenThrows() {
        Assertions.assertThrows(ArithmeticException.class, () -> Calculator.pow(-3.5, 5.5));
    }

    /*
     * abs
     */

    @Test
    public void givenPositive_whenAbs_thenReturnsSame() {
        Assertions.assertEquals(1.23456789, Calculator.abs(1.23456789));
    }

    @Test
    public void givenNegative_whenAbs_thenReturnsPositive() {
        Assertions.assertEquals(1.23456789, Calculator.abs(-1.23456789));
    }

    /*
     * exp
     */
    @Test
    public void givenPositiveDouble_whenExp_thenConverges() {
        Assertions.assertTrue(Calculator.abs(2.71828182846 - Calculator.exp(1)) < Calculator.EPSILON_REL * 2.71828182846);
        Assertions.assertTrue(Calculator.abs(485165195.40979 - Calculator.exp(20)) < Calculator.EPSILON_REL * 485165195.40979);
        Assertions.assertTrue(Calculator.abs(5184705528587070000000.0 - Calculator.exp(50)) < Calculator.EPSILON_REL * 5184705528587070000000.0);
    }

    @Test
    public void givenZero_whenExp_thenReturnsOne() {
        Assertions.assertEquals(1, Calculator.exp(0));
    }

    @Test
    public void givenNegativeDouble_whenExp_thenConverges() {
        Assertions.assertTrue(Calculator.abs(0.367879441171 - Calculator.exp(-1)) < Calculator.EPSILON_REL * 0.367879441171);
        Assertions.assertTrue(Calculator.abs(2.06115362244e-9 - Calculator.exp(-20)) < Calculator.EPSILON_REL * 2.06115362244e-9);
        Assertions.assertTrue(Calculator.abs(1.92874984796e-22 - Calculator.exp(-50)) < Calculator.EPSILON_REL * 1.92874984796e-22);
    }

    /*
     * factorial
     */
    @Test
    public void givenPositiveNonInteger_whenFactorial_thenThrows() {
        Assertions.assertThrows(ArithmeticException.class, () -> Calculator.factorial(7.5));
    }

    @Test
    public void givenNegativeInteger_whenFactorial_thenThrows() {
        Assertions.assertThrows(ArithmeticException.class, () -> Calculator.factorial(-12));
    }

    @Test
    public void givenPositiveInteger_whenFactorial_thenEvaluates() {
        Assertions.assertEquals(120, Calculator.factorial(5));
        Assertions.assertEquals(1, Calculator.factorial(0));
        Assertions.assertEquals(1, Calculator.factorial(1));
        Assertions.assertEquals(3628800, Calculator.factorial(10));
    }
    
    /*
     * ln
     */
    @Test
    public void givenNonPositive_whenLn_thenRejects() {
        Assertions.assertThrows(ArithmeticException.class, ()->Calculator.ln(-12));
        Assertions.assertThrows(ArithmeticException.class, ()->Calculator.ln(0));
    }

    @Test
    public void givenPositive_whenLn_thenConverges() {
        Assertions.assertTrue(Calculator.abs(0.69314718056 - Calculator.ln(2)) < Calculator.EPSILON_REL * 0.69314718056);
        Assertions.assertTrue(Calculator.abs(6.23832462504 - Calculator.ln(512)) < Calculator.EPSILON_REL * 6.23832462504);
        Assertions.assertTrue(Calculator.abs(472.029944064 - Calculator.ln(1e205)) < Calculator.EPSILON_REL * 472.029944064);
    }
}