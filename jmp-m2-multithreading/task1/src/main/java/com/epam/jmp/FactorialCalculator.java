package com.epam.jmp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * FactorialCalculator
 * Date: 02/20/2023
 *
 * @author Yauheni Antsipenka
 */
public final class FactorialCalculator {

    private static final Logger LOGGER = LoggerFactory.getLogger(FactorialCalculator.class);

    private FactorialCalculator() {
    }

    public static void calculate(int number) {
        long startTime = System.nanoTime();
        BigInteger result = DefaultFactorialCalculator.calculate(number);
        long endTime = System.nanoTime();
        LOGGER.info("Factorial of {} is: {} Time to execute: {}", number, result, endTime - startTime);
    }

    public static void calculateAsynchronously(int number) throws ExecutionException, InterruptedException {
        long startTime = System.nanoTime();
        BigInteger result = new ForkJoinPool().submit(new FactorialRecursiveTask(BigInteger.valueOf(number))).get();
        long endTime = System.nanoTime();
        LOGGER.info("Factorial of {} is: {} Time to execute: {}", number, result, endTime - startTime);
    }
}
