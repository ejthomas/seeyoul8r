package com.ejthomas.backend;

import java.util.HashSet;
import java.util.Collections;
import java.util.LinkedList;

public class Calculator {
    public static void main(String[] args) {
        // Receive input from frontend -- provided as String
        String input = "1++2";

        Parser parser = new Parser(input);

        parser.evaluate();

        if (parser.isEvaluated()) {
            System.out.print(input + "=" + parser.getResult() + "\n");
        }
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
}