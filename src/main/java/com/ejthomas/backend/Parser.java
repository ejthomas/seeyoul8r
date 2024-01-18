package com.ejthomas.backend;

public class Parser {
    
    private String input;
    private boolean evaluated = false;
    private int pos = 0;
    private int result;

    public Parser(String s) {
        input = s;
    }

    public boolean isEvaluated() {
        return evaluated;
    }

    public int getResult() {
        return result;
    }

    public void evaluate() {
        pos = 0;
        char c = input.charAt(pos);
        int num1 = 0;
        if (isDigit(c)) {
            // Handle digit
            num1 = getNumber();
        } else {
            error("[0-9]");
            return;
        }
        c = input.charAt(pos);
        if (isAddOp(c)) {
            switch (c) {
                case '+':
                    result = add(num1);
                    // evaluated = true;
                    break;
                case '-':
                    result = sub(num1);
                    // evaluated = true;
                    break;
                default:
                    break;
            }
            // return;
        } else if (isMulOp(c)) {
            switch (c) {
                case '*':
                    result = mul(num1);
                    // evaluated = true;
                    break;
                case '/':
                    result = div(num1);
                    // evaluated = true;
                    break;
                default:
                    break;
            }
            // return;
        } else {
            error("[+-*/]");
            return;
        }
        if (pos == input.length()) {
            evaluated = true;
        }
    }

    private char nextChar() {
        if (++pos < input.length()) {
            return input.charAt(pos);
        } else {
            return '\0';
        }
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isAddOp(char c) {
        return c == '+' || c == '-';
    }

    private boolean isMulOp(char c) {
        return c == '*' || c == '/';
    }

    private int getNumber() {
        int initPos = pos;
        while (isDigit(nextChar())) {}
        return Integer.parseInt(input.substring(initPos, pos));
    }

    private int add(int num1) {
        char c = nextChar();
        int num2;
        if (isDigit(c)) {
            num2 = getNumber();
        } else {
            error("[0-9]");
            return 0;
        }
        return num1 + num2;
    }

    private int sub(int num1) {
        char c = nextChar();
        int num2;
        if (isDigit(c)) {
            num2 = getNumber();
        } else {
            error("[0-9]");
            return 0;
        }
        return num1 - num2;
    }

    private int mul(int num1) {
        char c = nextChar();
        int num2;
        if (isDigit(c)) {
            num2 = getNumber();
        } else {
            error("[0-9]");
            return 0;
        }
        return num1 * num2;
    }

    private int div(int num1) {
        char c = nextChar();
        int num2;
        if (isDigit(c)) {
            num2 = getNumber();
        } else {
            error("[0-9]");
            return 0;
        }
        return num1 / num2;
    }

    private void error(String expected) {
        System.out.println("Error: expected " + expected);
    }
}
