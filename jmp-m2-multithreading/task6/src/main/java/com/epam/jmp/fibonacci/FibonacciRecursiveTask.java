package com.epam.jmp.fibonacci;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

/**
 * FibonacciRecursiveTask
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class FibonacciRecursiveTask extends RecursiveTask<BigInteger> {

    private final BigInteger number;

    public FibonacciRecursiveTask(BigInteger number) {
        this.number = number;
    }

    public FibonacciRecursiveTask(int number) {
        this.number = BigInteger.valueOf(number);
    }

    @Override
    protected BigInteger compute() {
        if (number.compareTo(BigInteger.ONE) <= 0) {
            return number;
        }
        FibonacciRecursiveTask fibonacciRecursiveTask1 =
            new FibonacciRecursiveTask(number.subtract(BigInteger.TWO));
        fibonacciRecursiveTask1.fork();
        FibonacciRecursiveTask fibonacciRecursiveTask2 =
            new FibonacciRecursiveTask(number.subtract(BigInteger.ONE));
        fibonacciRecursiveTask2.fork();
        return fibonacciRecursiveTask1.join().add(fibonacciRecursiveTask2.join());
    }
}
