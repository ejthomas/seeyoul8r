package com.ejthomas.backend;

public class Addition extends BinaryOperation {

    // private Operand operand1;
    // private Operand operand2;
    
    // public Addition(Operand operand1, Operand operand2) {
    //     super(2);
    //     this.operand1 = operand1;
    //     this.operand2 = operand2;
    // }

    public Addition(Expression leftOperand, Expression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public Literal evaluate() {
        return new Literal(leftOperand.evaluate().getValue()
            + rightOperand.evaluate().getValue());
    }

}
