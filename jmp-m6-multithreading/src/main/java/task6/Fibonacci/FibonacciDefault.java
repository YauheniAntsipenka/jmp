package task6.Fibonacci;

import java.math.BigInteger;

/**
 * FibonacciDefault
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public final class FibonacciDefault {

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
