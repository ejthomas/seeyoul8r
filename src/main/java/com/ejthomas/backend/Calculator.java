package com.ejthomas.backend;

public class Calculator {

    public static int pow(int base, int exponent) {
        return powBySquaring(base, exponent);
    }

    private static int powBySquaring(int base, int exponent) {
        /*
         * Cases:
         * - base and exponent == 0
         * - base != 0, exponent == 0
         * - base > 0, exponent < 0
         * - base == 0, exponent < 0
         * - exponent > 0
         */
        if (exponent == 0) {
            if (base == 0) {
                throw new ArithmeticException("0/0 is undefined");
            } else {
                return 1;
            }
        }
        if (exponent < 0) {
            // Throws ArithmeticException for base == 0
            return powBySquaring(1 / base, -exponent);
        }
        // Use tail-recursive iteration
        return powBySquaringIteration(1, base, exponent);
    }

    private static int powBySquaringIteration(int carry, int base, int exponent) {
        if (exponent == 1) {
            return carry * base;
        }
        if (exponent % 2 == 0) {
            return powBySquaringIteration(carry, base * base, exponent / 2);
        }
        // exponent % 2 == 1
        // This means exponent / 2 == (exponent - 1) / 2
        return powBySquaringIteration(carry * base, base * base, exponent / 2);
    }
}