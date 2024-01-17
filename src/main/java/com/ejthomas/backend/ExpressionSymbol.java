package com.ejthomas.backend;

public abstract class ExpressionSymbol extends Symbol {

    public ExpressionSymbol(String s) {
        super(s);
    }
    
    abstract Expression asExpression();
}
