package com.ejthomas.backend;

import java.util.Scanner;
import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedList;

public class Calculator {
    public static void main(String[] args) {
        // System.out.println("Hello, World!");
        // Scanner scanner = new Scanner(System.in);
        // String userInput = scanner.nextLine();
        // System.out.println("User input: " + userInput);
        // scanner.close();

        Literal i1 = new Literal(2);
        Literal i2 = new Literal(15);

        Expression addition = new Addition(i1, i2);
        Literal i3 = addition.evaluate();
        System.out.println("Result: " + i3.getValue());

        Expression multiplication = new Multiplication(i1, i2);
        Literal i4 = multiplication.evaluate();
        System.out.println("Result: " + i4.getValue());

        Expression subtraction = new Subtraction(i2, i1);
        Literal i5 = subtraction.evaluate();
        System.out.println("Result: " + i5.getValue());

        Expression division = new Division(i2, i1);
        Literal i6 = division.evaluate();
        System.out.println("Result: " + i6.getValue());

        // Nested expression tests
        Literal one = new Literal(1);
        Literal two = new Literal(2);
        Literal three = new Literal(3);
        Literal forty = new Literal(40);
        Literal five = new Literal(5);
        Expression mult = new Multiplication(two, three);
        Expression divide = new Division(forty, five);
        Expression add = new Addition(one, mult);
        Expression nested = new Subtraction(add, divide);
        System.out.println("Result: " + nested.evaluate().getValue());

        // Sequence of characters
        // LinkedList<String> ll = new LinkedList<>();
        // Scanner scanner = new Scanner(System.in);
        // String userInput = scanner.nextLine();
        // ll.add(userInput);
        // while (userInput.length() == 1) {
        //     userInput = scanner.nextLine();
        //     ll.add(userInput);
        // }
        // scanner.close();
        // System.out.println("User input: " + ll);

        // Sequence of symbols
        LinkedList<Symbol> ll = new LinkedList<>();
        ll.add(new Symbol("1"));
        ll.add(new Symbol("+"));
        ll.add(new Symbol("1"));

        // Rule 1
        HashSet<String> digits = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            digits.add(String.valueOf(i));
        }
        System.out.println(digits);

        
    }

    // public LiteralExpression parseString(String s) {
        // TODO: parentheses
        // HashSet<String> operators = new HashSet<>();
        // operators.add("+");
        // operators.add("-");
        // operators.add("*");
        // operators.add("/");
    // }
}