package com.ejthomas.backend;

public abstract class BinaryOperation extends Operation {
    
    protected Expression leftOperand;
    protected Expression rightOperand;

    public BinaryOperation(Expression leftOperand, Expression rightOperand) {
        super(2);
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }
}
