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
        Assertions.assertEquals(true, parser.isEvaluated());
        Assertions.assertEquals(136, parser.getResult());

        // Subtract
        parser = new Parser("52-30");
        parser.evaluate();
        Assertions.assertEquals(true, parser.isEvaluated());
        Assertions.assertEquals(22, parser.getResult());

        // Multiply
        parser = new Parser("4*16");
        parser.evaluate();
        Assertions.assertEquals(true, parser.isEvaluated());
        Assertions.assertEquals(64, parser.getResult());

        // Exact divide
        parser = new Parser("34/2");
        parser.evaluate();
        Assertions.assertEquals(true, parser.isEvaluated());
        Assertions.assertEquals(17, parser.getResult());

        // Rounding towards zero divide
        parser = new Parser("49/2");
        parser.evaluate();
        Assertions.assertEquals(true, parser.isEvaluated());
        Assertions.assertEquals(24, parser.getResult());

        // Rejects letter at start
        parser = new Parser("A2/2");
        parser.evaluate();
        Assertions.assertEquals(false, parser.isEvaluated());

        // Rejects letter at end
        parser = new Parser("1+1h");
        parser.evaluate();
        Assertions.assertEquals(false, parser.isEvaluated());

        // Rejects letter as first operand
        parser = new Parser("a*5");
        parser.evaluate();
        Assertions.assertEquals(false, parser.isEvaluated());

        // Rejects letter as second operand
        parser = new Parser("1-c");
        parser.evaluate();
        Assertions.assertEquals(false, parser.isEvaluated());

        // Rejects unsupported operator
        parser = new Parser("2^5");
        parser.evaluate();
        Assertions.assertEquals(false, parser.isEvaluated());
    }

    @Test
    public void givenEmptyString_whenEvaluate_thenExitsGracefully() {
        Parser parser = new Parser("");
        parser.evaluate();
        Assertions.assertEquals(false, parser.isEvaluated());
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

}
