package com.epam.jmp.fibonacci;

import java.math.BigInteger;

/**
 * DefaultFibonacciCalculator
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public final class DefaultFibonacciCalculator {

    public static BigInteger calculate(long n) {
        long first = 0;
        long second = 1;
        long result = n;
        for (int i = 1; i < n; i++) {
            result = first + second;
            first = second;
            second = result;
        }
        return BigInteger.valueOf(result);
    }
}
