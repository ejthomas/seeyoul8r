package com.ejthomas.backend;

public class Calculator {

    public final static double EPSILON_REL = 1e-10;
    public final static int MAX_ITERATIONS = 1_000_000;

    /*
     * Power function
     */
    public static double pow(double base, double exponent) {
        if (exponent == (int)exponent) {
            // Exponent is integer
            return powBySquaring(base, (int)exponent);
        } else {
            return powByLogarithm(base, exponent);
        }
    }

    private static double powBySquaring(double base, int exponent) {
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
            if (base == 0) {
                throw new ArithmeticException("1/0 is undefined");
            }
            return powBySquaring(1 / base, -exponent);
        }
        // Use tail-recursive iteration
        return powBySquaringIteration(1, base, exponent);
    }

    private static double powBySquaringIteration(double carry, double base, int exponent) {
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

    private static double powByLogarithm(double base, double exponent) {
        /*
         * ln(pow(base, exponent)) == exponent * ln(base)
         * Therefore pow(base, exponent) == exp(ln(pow(base, exponent)))
         * pow(base, exponent) == exp(exponent * ln(base))
         */
        if (base == 0 && exponent > 0) {
            return 0;
        } else if (base < 0 || (base == 0 && exponent <= 0)) {
            throw new ArithmeticException(String.format("%f^%f is undefined", base, exponent));
        }
        return exp(exponent * ln(base));
    }

    /*
     * Absolute value
     */
    public static double abs(double x) {
        return x < 0 ? -x : x;
    }

    /*
     * Exponential function (e^x)
     */
    public static double exp(double x) {
        if (x < 0) {
            // Alternating term signs cause slow convergence, overflow
            // Computing 1/exp(-x) in this case avoids this
            return 1.0 / exp(-x);
        }
        double newTerm = 1;
        double result = 0;
        int n = 0;  // term counter
        while (abs(newTerm) > EPSILON_REL * result) {
            if (n > MAX_ITERATIONS) {
                throw new ArithmeticException(String.format("exp(%f) did not converge", x));
            }
            result += newTerm;
            newTerm = expNextTerm(x, ++n, newTerm);
        }
        return result + newTerm;
    }

    private static double expNextTerm(double x, int n, double lastTerm) {
        /*
         * exp(x) == sum_(n=1,n=infinity) pow(x, n) / factorial(n)
         * pow(x, n) / factorial(n) == (pow(x, n - 1) / factorial(n - 1)) * x / n
         */
        return lastTerm * x / n;
    }

    /*
     * Factorial
     */
    public static double factorial(double x) {
        if (x == (long)x) {
            // Value of x is integer
            return trueFactorial((long)x);
        } else {
            throw new ArithmeticException("Factorial of non-integer");
        }
    }

    private static double trueFactorial(long x) {
        if (x < 0) {
            throw new ArithmeticException("Factorial of negative number");
        }
        // Tail-recursive
        return trueFactorialIteration(1, x);
    }

    private static double trueFactorialIteration(double carry, long x) {
        if (x == 0) {
            return carry;
        } else {
            return trueFactorialIteration(carry * x, x - 1);
        }
    }

    /*
     * Natural logarithm
     */
    public static double ln(double x) {
        if (x <= 0) {
            throw new ArithmeticException("ln(x) not real for x <= 0");
        }
        double previousResult = 1;
        double result = lnFixedPointIteration(x, previousResult);
        int numIterations = 1; 
        while (abs(result - previousResult) > EPSILON_REL * result) {
            if (numIterations++ > MAX_ITERATIONS) {
                throw new ArithmeticException(String.format("ln(%f) did not converge", x));
            }
            previousResult = result;
            result = lnFixedPointIteration(x, result);
        }
        return result;
    }

    private static double lnFixedPointIteration(double x, double currentEstimate) {
        return currentEstimate + 2 * (x - exp(currentEstimate)) / (x + exp(currentEstimate));
    }
}