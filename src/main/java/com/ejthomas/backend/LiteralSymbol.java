package com.ejthomas.backend;

public class LiteralSymbol extends ExpressionSymbol {

    public LiteralSymbol(String s) {
        super(s);
    }
    
    public int asInteger() {
        return Integer.parseInt(value);
    }

    public Literal asExpression() {
        return new Literal(asInteger());
    }
}
