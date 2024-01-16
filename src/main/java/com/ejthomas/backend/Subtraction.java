package com.ejthomas.backend;

public class Subtraction extends BinaryOperation {
    
    public Subtraction(Expression leftOperand, Expression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public Literal evaluate() {
        return new Literal(leftOperand.evaluate().getValue() 
            - rightOperand.evaluate().getValue());
    }
}
