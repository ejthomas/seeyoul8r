package com.ejthomas.backend;

public class Division extends BinaryOperation {
    
    public Division(Expression leftOperand, Expression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public Literal evaluate() {
        return new Literal(leftOperand.evaluate().getValue() 
            / rightOperand.evaluate().getValue());
    }
}
