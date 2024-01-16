package com.ejthomas.frontend;

public class LiteralSymbol {
    private String value;

    public LiteralSymbol(String s) {
        value = s;
    }

    public int asInteger() {
        return Integer.parseInt(value);
    }
}
