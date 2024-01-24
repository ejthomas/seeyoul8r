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
        // This check feels very wrong
        // if (pos == input.length()) {
        //     // result = workingResult;
        //     // evaluated = true;
        //     return expression;
        // }
        if (idx < input.length()) {
            // char c = input.charAt(idx);
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
         * operators. This includes numbers and expressions
         * enclosed in parentheses e.g. (expression).
         */
        if (c == '(') {
            nextChar();
            int factor = getExpression();
            if (c == ')') {
                nextChar();
                return factor;
            } else {
                error(")");
                return 0;
            }
        } else {
            return getNumber();
        }
    }

    private int getNumber() {
        // char c = input.charAt(idx);
        // Handle sign if present
        if (c == '+') {
            // Skip with no action
            nextChar();
        } else if (c == '-') {
            nextChar();
            return sub(0); // return 0 - <numerical part>
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
        return num1 + getTerm();
    }

    private int sub(int num1) {
        return num1 - getTerm();
    }

    private int mul(int num1) {
        return num1 * getFactor();
    }

    private int div(int num1) {
        return num1 / getFactor();
    }

    private void error(String expected) {
        if (!errorRaised) {
            lastError = "Expected " + expected;
            System.out.println("Error: " + lastError);
        }
        errorRaised = true;
    }
}
