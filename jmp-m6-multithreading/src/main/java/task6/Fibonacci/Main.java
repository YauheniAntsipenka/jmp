package task6.Fibonacci;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * Main
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int number = 45;
        long startTime = System.nanoTime();
        LOGGER.info("Fibonacci sum of {} is: {}", number, FibonacciDefault.calculate(number));
        long endTime = System.nanoTime();
        LOGGER.info("Time to execute: {}", endTime - startTime);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        startTime = System.nanoTime();
        ForkJoinTask<BigInteger> fibonacci = forkJoinPool.submit(new FibonacciRecursiveTask(
            BigInteger.valueOf(number)));
        LOGGER.info("Fibonacci of {} is: {}", number, fibonacci.get());
        endTime = System.nanoTime();
        LOGGER.info("Time to execute: {}", endTime - startTime);
    }
}
