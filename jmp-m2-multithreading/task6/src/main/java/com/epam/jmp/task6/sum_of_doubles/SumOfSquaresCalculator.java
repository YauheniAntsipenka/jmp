package com.epam.jmp.task6.sum_of_doubles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

/**
 * SumOfSquaresCalculator
 * Date: 02/20/2023
 *
 * @author Yauheni Antsipenka
 */
public final class SumOfSquaresCalculator {

    private static final Logger LOGGER = LoggerFactory.getLogger(SumOfSquaresCalculator.class);

    private SumOfSquaresCalculator() {};

    public static void calculate(double[] array) {
        long startTime = System.nanoTime();
        double sum = Arrays.stream(array).sum();
        long endTime = System.nanoTime();
        LOGGER.info("sum: {}. Time to execute(linear): {}", sum, endTime - startTime);
    }

    public static void calculateAsynchronously(double[] array) {
        long startTime = System.nanoTime();
        double sum = sumOfSquares(new ForkJoinPool(), array);
        long endTime = System.nanoTime();
        LOGGER.info("sum: {}. Time to execute(parallel): {}", sum, endTime - startTime);
    }

    private static double sumOfSquares(ForkJoinPool pool, double[] array) {
        int n = array.length;
        Applyer a = new Applyer(array, 0, n, null);
        pool.invoke(a);
        return a.result;
    }
}
