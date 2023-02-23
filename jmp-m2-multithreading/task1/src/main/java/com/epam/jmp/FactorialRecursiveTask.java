package com.epam.jmp;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

/**
 * Factorial
 * Date: 02/04/2023
 *
 * @author Yauheni Antsipenka
 */
public class FactorialRecursiveTask extends RecursiveTask<BigInteger> {

    private final BigInteger number;

    public FactorialRecursiveTask(BigInteger number) {
        this.number = number;
    }

    @Override
    protected BigInteger compute() {
        if (number.compareTo(BigInteger.ONE) <= 0) {
            return BigInteger.ONE;
        }
        FactorialRecursiveTask factorialSub1 = new FactorialRecursiveTask(number.subtract(BigInteger.ONE));
        factorialSub1.fork();
        return number.multiply(factorialSub1.join());
    }
}
