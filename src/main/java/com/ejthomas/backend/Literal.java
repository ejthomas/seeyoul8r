package com.ejthomas.backend;

public class Literal extends Expression {
    
    private int value;

    public Literal(int value) {
        this.value = value;
    }

    @Override
    public Literal evaluate() {
        return this;
    }

    public int getValue() {
        return value;
    }
}
