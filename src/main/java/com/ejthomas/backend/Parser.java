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
        /*  When calling subroutines from this method, the current
            position should be set on the first character needed
            by the subroutine (i.e. subroutines should not have 
            to start by advancing to the next character)
        */
        pos = 0;
        if (pos == input.length()) {
            error("non-empty");
            return;  // exit with error if input is empty
        }
        char c = input.charAt(pos);
        int workingResult = getNumber();
        if (pos == input.length()) {
            result = workingResult;
            evaluated = true;
            return;
        }
        c = input.charAt(pos);
        switch (c) {
            // AddOps
            case '+':
                c = nextChar();
                workingResult = add(workingResult);
                break;
            case '-':
                c = nextChar();
                workingResult = sub(workingResult);
                break;
            // MulOps
            case '*':
                c = nextChar();
                workingResult = mul(workingResult);
                break;
            case '/':
                c = nextChar();
                workingResult = div(workingResult);
                break;
            default:
                error("[+-*/]");
                return;
        }
        if (pos == input.length()) {
            evaluated = true;
            result = workingResult;
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
        char c = input.charAt(pos);
        // Handle sign if present
        if (c == '+') {
            // Skip with no action
            c = nextChar();
        } else if (c == '-') {
            c = nextChar();
            return sub(0); // return 0 - <numerical part>
        }
        // Get numerical part
        if (isDigit(c)) {
            int initPos = pos;
            while (isDigit(nextChar())) {}
            return Integer.parseInt(input.substring(initPos, pos));
        } else {
            error("[0-9]");
            return 0;
        }
    }

    private int add(int num1) {
        return num1 + getNumber();
    }

    private int sub(int num1) {
        return num1 - getNumber();
    }

    private int mul(int num1) {
        return num1 * getNumber();
    }

    private int div(int num1) {
        return num1 / getNumber();
    }

    private void error(String expected) {
        System.out.println("Error: expected " + expected);
    }
}
