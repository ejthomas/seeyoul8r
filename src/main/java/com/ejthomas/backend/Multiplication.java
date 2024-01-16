package com.ejthomas.backend;

public class Multiplication extends BinaryOperation {
    
    public Multiplication(Expression leftOperand, Expression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public Literal evaluate() {
        return new Literal(leftOperand.evaluate().getValue() 
            * rightOperand.evaluate().getValue());
    }
}
