package com.epam.jmp.fibonacci;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * Runner
 * Date: 02/20/2023
 *
 * @author Yauheni Antsipenka
 */
public final class FibonacciCalculator {

    private static final Logger LOGGER = LoggerFactory.getLogger(FibonacciCalculator.class);

    private FibonacciCalculator() {}

    public static void calculate(int number) {
        long startTime = System.nanoTime();
        BigInteger result = DefaultFibonacciCalculator.calculate(number);
        long endTime = System.nanoTime();
        LOGGER.info("Fibonacci sum of {} is: {}. Time to execute: {}", number, result, endTime - startTime);
    }

    public static void calculateAsynchronously(int number) throws ExecutionException, InterruptedException {
        long startTime = System.nanoTime();
        BigInteger result = new ForkJoinPool().submit(new FibonacciRecursiveTask(BigInteger.valueOf(number))).get();
        long endTime = System.nanoTime();
        LOGGER.info("Fibonacci sum of {} is: {}. Time to execute: {}", number, result, endTime - startTime);
    }
}
