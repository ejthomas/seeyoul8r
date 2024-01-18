package com.ejthomas.backend;

import java.util.Scanner;
import java.util.HashSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
// import java.util.regex.Pattern;
// import java.util.regex.Matcher;

public class Calculator {
    public static void main(String[] args) {
        // Receive input from frontend -- provided as String
        String input = "1+2*3-4/5";

        LinkedList<String> sequence = toSequence(input);
        System.out.println(sequence);

        String input2 = "11+12*3-40/5";

        LinkedList<String> sequence2 = toSequence(input2);
        System.out.println(sequence2);

        String input3 = "1+sqrtq20*3-4/5";

        LinkedList<String> sequence3 = toSequence(input3);
        System.out.println(sequence3);

        LinkedList<Token> input4 = new LinkedList<>(){{
            add(new Token("1", false));
            add(new Token("+", true));
            add(new Token("sqrt", true));
            add(new Token("2", false));
            add(new Token("0", false));
            add(new Token("*", true));
            add(new Token("3", false));
            add(new Token("-", true));
            add(new Token("4", false));
            add(new Token("/", true));
            add(new Token("5", false));
        }};
        LinkedList<Token> sequence4 = combineDigits(input4);
        // Print
        System.out.print("[");
        for (int i = 0; i < sequence4.size(); i++) {
            System.out.print(sequence4.get(i).getValue());
            if (i != sequence4.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]\n");
    }

    public static LinkedList<String> toSequence(String s) {
        LinkedList<String> sequence = new LinkedList<>();
        HashSet<String> operators = new HashSet<String>();
        Collections.addAll(operators, "+", "-", "*", "/", "sqrt");
        int i = 0;
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                // Handle groups of digits
                int start = i;
                while (++i < s.length() && Character.isDigit(s.charAt(i))) {}
                sequence.add(s.substring(start, i));
            } else {
                // Handle operators
                int start = i;
                while (++i < s.length() && !Character.isDigit(s.charAt(i))) {}
                String subs = s.substring(start, i);
                int j = 0;
                while (++j <= subs.length()) {
                    if (operators.contains(subs)) {
                        sequence.add(subs);
                        subs = "";
                    } else {
                        if (operators.contains(subs.substring(0, j))) {
                            sequence.add(subs.substring(0, j));
                            subs = subs.substring(j); // subs.substring(j).length() == 0 if j == subs.length()
                            j = 0;
                        }
                    }
                }
                // Check all operators parsed
                if (subs.length() > 0) {
                    System.out.println("Error: could not parse operator: " + subs);
                }
            }
        }
        return sequence;
    }

    public static LinkedList<Token> combineDigits(LinkedList<Token> ll) {
        LinkedList<Token> sequence = new LinkedList<>();
        int i = 0;
        while (i < ll.size()) {
            if (!(ll.get(i).isOperator)) {
                // Handle groups of digits
                int start = i;
                while (++i < ll.size() && !ll.get(i).isOperator) {}
                LinkedList<String> contents = new LinkedList<>();
                for (Token t: ll.subList(start, i)) {
                    contents.add(t.getValue());
                }
                String value = String.join("", contents);
                Token t = new Token(value, false);
                sequence.add(t);
            } else {
                sequence.add(ll.get(i++));
            }
        }
        return sequence;
    }
}