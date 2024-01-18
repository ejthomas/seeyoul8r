package com.ejthomas.backend;

public class OperatorToken {
    
    private String value;
    public boolean isOperator;

    public OperatorToken(String value) {
        this.value = value;
        this.isOperator = isOperator;
    }

    public String getValue() {
        return value;
    }
}
