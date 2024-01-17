package com.ejthomas.backend;

public abstract class UnaryOperation extends Operation {
    
    protected Literal operand;

    public UnaryOperation(Literal operand) {
        // super(1);
        this.operand = operand;
    }
}
