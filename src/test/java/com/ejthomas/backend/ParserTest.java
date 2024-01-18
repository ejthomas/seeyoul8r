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
        Parser parser = new Parser("1+2");
        Assertions.assertEquals(false, parser.isEvaluated());
        parser.evaluate();
        Assertions.assertEquals(true, parser.isEvaluated());
        parser = new Parser("1++2");
        parser.evaluate();
        Assertions.assertEquals(false, parser.isEvaluated());
    }

    @Test
    public void getResult() {
        Parser parser = new Parser("1+2");
        Assertions.assertEquals(0, parser.getResult());
        parser.evaluate();
        Assertions.assertEquals(3, parser.getResult());
        parser = new Parser("1++2");
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

    }

    @Test
    public void nextChar() {
    }

    @Test
    public void isDigit() {
    }

    @Test
    public void isAddOp() {
    }

    @Test
    public void isMulOp() {
    }

    @Test
    public void getNumber() {
    }

    @Test
    public void add() {
    }

    @Test
    public void sub() {
    }

    @Test
    public void mul() {
    }

    @Test
    public void div() {
    }

    @Test
    public void error() {
    }
}
