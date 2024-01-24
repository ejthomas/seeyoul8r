package com.ejthomas.backend;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ParserTest {
    
    @Test
    public void constructor() {
        Parser parser = new Parser("1+2");
        Assertions.assertInstanceOf(Parser.class, parser);
    }

    @Test
    public void isEvaluated() {
        // Defaults to false
        Parser parser = new Parser("1+2");
        Assertions.assertEquals(false, parser.isEvaluated());

        // True after evaluating valid expression
        parser.evaluate();
        Assertions.assertEquals(true, parser.isEvaluated());

        // False after evaluating invalid expression
        parser = new Parser("1+*2");
        parser.evaluate();
        Assertions.assertEquals(false, parser.isEvaluated());
    }

    @Test
    public void getResult() {
        Parser parser = new Parser("1+2");
        Assertions.assertEquals(0, parser.getResult());
        parser.evaluate();
        Assertions.assertEquals(3, parser.getResult());
        parser = new Parser("1+*2");
        parser.evaluate();
        Assertions.assertEquals(0, parser.getResult());
    }

    @Test
    public void evaluate() {
        // Add
        Parser parser = new Parser("123+13");
        parser.evaluate();
        Assertions.assertTrue(parser.isEvaluated());
        Assertions.assertEquals(136, parser.getResult());

        // Subtract
        parser = new Parser("52-30");
        parser.evaluate();
        Assertions.assertTrue(parser.isEvaluated());
        Assertions.assertEquals(22, parser.getResult());

        // Multiply
        parser = new Parser("4*16");
        parser.evaluate();
        Assertions.assertTrue(parser.isEvaluated());
        Assertions.assertEquals(64, parser.getResult());

        // Exact divide
        parser = new Parser("34/2");
        parser.evaluate();
        Assertions.assertTrue(parser.isEvaluated());
        Assertions.assertEquals(17, parser.getResult());

        // Rounding towards zero divide
        parser = new Parser("49/2");
        parser.evaluate();
        Assertions.assertTrue(parser.isEvaluated());
        Assertions.assertEquals(24, parser.getResult());

        // Rejects letter at start
        parser = new Parser("A2/2");
        parser.evaluate();
        Assertions.assertFalse(parser.isEvaluated());

        // Rejects letter at end
        parser = new Parser("1+1h");
        parser.evaluate();
        Assertions.assertFalse(parser.isEvaluated());

        // Rejects letter as first operand
        parser = new Parser("a*5");
        parser.evaluate();
        Assertions.assertFalse(parser.isEvaluated());

        // Rejects letter as second operand
        parser = new Parser("1-c");
        parser.evaluate();
        Assertions.assertFalse(parser.isEvaluated());

        // Rejects unsupported operator
        parser = new Parser("2&5");
        parser.evaluate();
        Assertions.assertFalse(parser.isEvaluated());
    }

    @Test
    public void givenEmptyString_whenEvaluate_thenExitsGracefully() {
        Parser parser = new Parser("");
        parser.evaluate();
        Assertions.assertFalse(parser.isEvaluated());
    }

    @Test
    public void givenUnaryPlusMinus_whenEvaluate_thenGivesCorrectAnswer() {
        Parser parser = new Parser("-1+23");
        parser.evaluate();
        Assertions.assertTrue(parser.isEvaluated());
        Assertions.assertEquals(22, parser.getResult());

        parser = new Parser("1+-23");
        parser.evaluate();
        Assertions.assertTrue(parser.isEvaluated());
        Assertions.assertEquals(-22, parser.getResult());

        parser = new Parser("+1++23");
        parser.evaluate();
        Assertions.assertTrue(parser.isEvaluated());
        Assertions.assertEquals(24, parser.getResult());
    }

    @Test
    public void givenTermAndFactor_whenEvaluate_thenPrecedenceCorrect() {
        // Add and multiply, higher precedence 2nd
        Parser parser = new Parser("1+2*3");
        parser.evaluate();
        Assertions.assertTrue(parser.isEvaluated());
        Assertions.assertEquals(7, parser.getResult());

        // Subtract and divide, higher precedence 2nd
        parser = new Parser("34-12/4");
        parser.evaluate();
        Assertions.assertTrue(parser.isEvaluated());
        Assertions.assertEquals(31, parser.getResult());

        // Subtract and multiply, higher precedence 1st
        parser = new Parser("2*15-3");
        parser.evaluate();
        Assertions.assertTrue(parser.isEvaluated());
        Assertions.assertEquals(27, parser.getResult());
    }

    @Test
    public void givenThreeTerms_whenEvaluate_thenPrecedenceCorrect() {
        Parser parser = new Parser("84-2+7");
        parser.evaluate();
        Assertions.assertTrue(parser.isEvaluated());
        Assertions.assertEquals(89, parser.getResult());
    }

    @Test
    public void givenThreeFactors_whenEvaluate_thenPrecedenceCorrect() {
        Parser parser = new Parser("3*4/6");
        parser.evaluate();
        Assertions.assertTrue(parser.isEvaluated());
        Assertions.assertEquals(2, parser.getResult());
    }

    @Test
    public void givenParentheses_whenEvaluate_thenPrecedenceCorrect() {
        Parser parser = new Parser("(1+2)*3");
        parser.evaluate();
        Assertions.assertEquals(9, parser.getResult());
    }

    @Test
    public void givenRedundantParens_whenEvaluate_thenAccepts() {
        // Whole input in redundant parentheses
        Parser parser = new Parser("(1+2*3)");
        parser.evaluate();
        Assertions.assertEquals(7, parser.getResult());

        // Single value in redundant parentheses
        parser = new Parser("1+(2)*3");
        parser.evaluate();
        Assertions.assertEquals(7, parser.getResult());
    }

    @Test
    public void givenUnmatchedParens_whenEvaluate_thenRejects() {
        // Rejects unmatched left parenthesis
        Parser parser = new Parser("1+(2*3");
        parser.evaluate();
        Assertions.assertFalse(parser.isEvaluated());

        // Rejects unexpected right parenthesis
        parser = new Parser("1+2)*3");
        parser.evaluate();
        Assertions.assertFalse(parser.isEvaluated());
    }

    @Test
    public void givenPower_whenEvaluate_thenAccepts() {
        Parser parser = new Parser("10^4");
        parser.evaluate();
        Assertions.assertEquals(10000, parser.getResult());

        parser = new Parser("2^10");
        parser.evaluate();
        Assertions.assertEquals(1024, parser.getResult());

        parser = new Parser("-1^2");
        parser.evaluate();
        Assertions.assertEquals(1, parser.getResult());

        parser = new Parser("10^-1");
        parser.evaluate();
        Assertions.assertTrue(parser.isEvaluated());
        Assertions.assertEquals(0, parser.getResult());
    }

    @Test
    public void givenExpressionWithPower_whenEvaluate_thenPrecedenceCorrect() {
        Parser parser = new Parser("(1+2)^2");
        parser.evaluate();
        Assertions.assertEquals(9, parser.getResult());

        parser = new Parser("2^(2+2)");
        parser.evaluate();
        Assertions.assertEquals(16, parser.getResult());

        parser = new Parser("5*2^2");
        parser.evaluate();
        Assertions.assertEquals(20, parser.getResult());

        parser = new Parser("3+4^2-1");
        parser.evaluate();
        Assertions.assertEquals(18, parser.getResult());

        parser = new Parser("2^3^2");
        parser.evaluate();
        Assertions.assertEquals(512, parser.getResult());
    }
}
