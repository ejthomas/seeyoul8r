package com.ejthomas.backend;

public class Parser {
    
    private String input;
    private boolean evaluated = false;
    private int idx = 0;  // current index in input
    private char c;  // current character in input
    private double result;
    private boolean errorRaised = false;
    private String lastError;
    private final String validCharacters = "[0-9+-*/^.()]";

    public Parser(String s) {
        input = s;
        c = input.length() > 0 ? input.charAt(idx): '\0';
    }

    public boolean isEvaluated() {
        return evaluated;
    }

    public double getResult() {
        return result;
    }

    public String getLastError() {
        return lastError;
    }

    public boolean isInErrorState() {
        return errorRaised;
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
            error("Expected non-empty");
            return;  // exit with error if input is empty
        }
        double workingResult = getExpression();
        // Check that end is reached rejects trailing characters
        if (idx == input.length() && !errorRaised) {
            evaluated = true;
            result = workingResult;
        } else {
            error("Expected " + validCharacters);
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

    private double getExpression() {
        double expression = getTerm();
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

    private double getTerm() {
        /* A term is an expression which can be an operand of the
         * add/subtract operators. This includes numbers and
         * binary expressions containing MulOps and factors.
         */
        double term = getFactor();
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

    private double getFactor() {
        /* A factor is a valid operand of the multiply/divide 
         * operators. This includes numbers, numbers raised 
         * to powers and expressions enclosed in parentheses 
         * e.g. (expression).
         */
        double factor;
        if (c == '(') {
            nextChar();
            factor = getExpression();
            if (c == ')') {
                nextChar();
            } else {
                error("Expected )");
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

    private double getNumber() {
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
            while (isDigit(c) || c == '.') { nextChar(); }
            return stringToDouble(input.substring(initIdx, idx));
        } else {
            error("Expected [0-9]");
            return 0;
        }
    }

    private double stringToDouble(String s) {
        double intPart = 0.0;
        double decimalPart = 0.0;
        int currentPos = 0;
        while (currentPos < s.length() && s.charAt(currentPos) != '.') {
            intPart = 10.0 * intPart + (s.charAt(currentPos) - '0');
            currentPos++;
        }
        if (currentPos == s.length()) { 
            return intPart; 
        } else {
            // Only reachable if decimal point present
            currentPos = s.length() - 1;
            while (s.charAt(currentPos) != '.') {
                decimalPart = 0.1 * (decimalPart + (s.charAt(currentPos) - '0'));
                currentPos--;
            }
            return intPart + decimalPart;
        }
    }

    private double add(double num1) {
        // TODO: check for overflow before evaluating
        return num1 + getTerm();
    }

    private double sub(double num1) {
        // TODO: check for overflow before evaluating
        return num1 - getTerm();
    }

    private double mul(double num1) {
        // TODO: check for overflow before evaluating
        return num1 * getFactor();
    }

    private double div(double num1) {
        // TODO: check for overflow before evaluating
        return num1 / getFactor();
    }

    private double pow(double base) {
        // TODO: check for overflow before evaluating
        try {
            return Calculator.pow(base, getFactor());
        } catch (ArithmeticException e) {
            error(e.getMessage());
            return 0;
        }
    }

    private void error(String message) {
        if (!errorRaised) {
            lastError = message;
            System.out.println("Error: " + lastError);
        }
        errorRaised = true;
    }
}
