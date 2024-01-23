# Implementation design

Example inputs: 
- 1 + 2
- 3 * 4
- 10 / 5

# Frontend

A Swing GUI allowing the user to type an expression using a displayed set of graphical buttons. Displays the output after evaluation of the input.

# Backend

Operates a string parser to evaluate expressions. Assumes only single-character operators for now. Numbers can have more than one digit, but currently using integer arithmetic only.

## Parser

Attributes:
- `private int pos`: index of current position
- `private String input`: string representing input expression
- `private boolean evaluated`: boolean flag set to true if evaluation completed successfully
- `private int result`: integer representing calculation result
Contains methods to parse an input string one character at a time.
- `public boolean isEvaluated()`: getter for private member `evaluated`
- `public int getResult()`: getter for private member `result`
- `public void evaluate()`: top-level method to compute result of input expression
- `private char nextChar()`: advances `pos` by 1 and returns next character of input
- `private boolean isDigit(char c)`: returns True if `c` is a digit (0-9)
- `private boolean isAddOp(char c)`: returns True if `c` is `'+'` or `'-'`
- `private boolean isMulOp(char c)`: returns True if `c` is `'*'` or `'/'`
- `private int getNumber()`: returns integer starting with character at current position, advancing `pos` to next character after number
- `private void error()`: handle error

Considering `java.text.StringCharacterIterator` as a way to streamline iteration through the string characters.

# Operator precedence

Unary operations are applied first, then the BIDMAS operator precedence rule is applied. This means that brackets are recursed upon before performing other operations, and expressions without parentheses are evaluated in the order *indices* (I), *division* (D)/*multiplication* (M) and *addition* (A)/*subtraction* (S), with operators of equal precedence evaluated left to right.

