package com.ejthomas.backend;

public class Token {
    
    private String value;
    public boolean isOperator;

    public Token(String value, boolean isOperator) {
        this.value = value;
        this.isOperator = isOperator;
    }

    public String getValue() {
        return value;
    }
}
