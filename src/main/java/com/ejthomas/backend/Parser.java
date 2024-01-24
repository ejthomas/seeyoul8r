package com.ejthomas.backend;

public class Parser {
    
    private String input;
    private boolean evaluated = false;
    private int idx = 0;  // current index in input
    private char c;  // current character in input
    private int result;
    private boolean errorRaised = false;
    private String lastError;
    private final String validCharacters = "[0-9+-*/]";

    public Parser(String s) {
        input = s;
        c = input.length() > 0 ? input.charAt(idx): '\0';
    }

    public boolean isEvaluated() {
        return evaluated;
    }

    public int getResult() {
        return result;
    }

    public String getLastError() {
        return lastError;
    }

    public void evaluate() {
        /*  When calling subroutines from this method, the current
            position should be set on the first character needed
            by the subroutine (i.e. subroutines should not have 
            to start by advancing to the next character)
        */
        errorRaised = false;  // overwrite any previous value
        idx = 0;
        if (idx == input.length()) {
            error("non-empty");
            return;  // exit with error if input is empty
        }
        int workingResult = getExpression();
        // Check that end is reached rejects trailing characters
        if (idx == input.length() && !errorRaised) {
            evaluated = true;
            result = workingResult;
        } else {
            error(validCharacters);
        }
    }

    private void nextChar() {
        if (++idx < input.length()) {
            c = input.charAt(idx);
        } else {
            c = '\0';
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

    private int getExpression() {
        int expression = getTerm();
        if (idx < input.length()) {
            while (isAddOp(c)) {
                if (c == '+') {
                    nextChar();
                    expression = add(expression);
                } else {
                    nextChar();
                    expression = sub(expression);
                }
            }
        }
        return expression;
    }

    private int getTerm() {
        /* A term is an expression which can be an operand of the
         * add/subtract operators. This includes numbers and
         * binary expressions containing MulOps and factors.
         */
        int term = getFactor();
        if (idx < input.length()) {
            while (isMulOp(c)) {
                if (c == '*') {
                    nextChar();
                    term = mul(term);
                } else {
                    nextChar();
                    term = div(term);
                }
            }
        } 
        return term;
    }

    private int getFactor() {
        /* A factor is a valid operand of the multiply/divide 
         * operators. This includes numbers, numbers raised 
         * to powers and expressions enclosed in parentheses 
         * e.g. (expression).
         */
        int factor;
        if (c == '(') {
            nextChar();
            factor = getExpression();
            if (c == ')') {
                nextChar();
            } else {
                error(")");
                return 0;
            }
        } else {
            factor = getNumber();
        }
        if (c == '^') {
            nextChar();
            factor = pow(factor);
        }
        return factor;
    }

    private int getNumber() {
        // Handle sign if present
        if (c == '+') {
            // Skip with no action
            nextChar();
        } else if (c == '-') {
            nextChar();
            return -getNumber();
        }
        // Get numerical part
        if (isDigit(c)) {
            int initIdx = idx;
            nextChar();
            while (isDigit(c)) { nextChar(); }
            return Integer.parseInt(input.substring(initIdx, idx));
        } else {
            error("[0-9]");
            return 0;
        }
    }

    private int add(int num1) {
        // TODO: check for overflow before evaluating
        return num1 + getTerm();
    }

    private int sub(int num1) {
        // TODO: check for overflow before evaluating
        return num1 - getTerm();
    }

    private int mul(int num1) {
        // TODO: check for overflow before evaluating
        return num1 * getFactor();
    }

    private int div(int num1) {
        // TODO: check for overflow before evaluating
        return num1 / getFactor();
    }

    private int pow(int base) {
        // TODO: check for overflow before evaluating
        return Calculator.pow(base, getFactor());
    }

    private void error(String expected) {
        if (!errorRaised) {
            lastError = "Expected " + expected;
            System.out.println("Error: " + lastError);
        }
        errorRaised = true;
    }
}
