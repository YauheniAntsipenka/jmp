package task6.Fibonacci;

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
public final class Runner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);

    private Runner() {}

    public static void calculateFibonacci(int number) {
        long startTime = System.nanoTime();
        BigInteger result = FibonacciDefault.calculate(number);
        long endTime = System.nanoTime();
        LOGGER.info("Fibonacci sum of {} is: {}. Time to execute: {}", number, result, endTime - startTime);
    }

    public static void calculateFibonacciAsynchronously(int number) throws ExecutionException, InterruptedException {
        long startTime = System.nanoTime();
        BigInteger result = new ForkJoinPool().submit(new FibonacciRecursiveTask(BigInteger.valueOf(number))).get();
        long endTime = System.nanoTime();
        LOGGER.info("Fibonacci sum of {} is: {}. Time to execute: {}", number, result, endTime - startTime);
    }
}
