package task1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * Main
 * Date: 02/04/2023
 *
 * @author Yauheni Antsipenka
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int number = 10;
        long startTime = System.nanoTime();
        LOGGER.info("Factorial of {} is: {}", number, FactorialDefault.get(number));
        long endTime = System.nanoTime();
        LOGGER.info("Time to execute: {}", endTime - startTime);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        startTime = System.nanoTime();
        ForkJoinTask<BigInteger> factorial = forkJoinPool.submit(new FactorialRecursiveTask(
            BigInteger.valueOf(number)));
        LOGGER.info("Factorial of {} is: {}", number, factorial.get());
        endTime = System.nanoTime();
        LOGGER.info("Time to execute: {}", endTime - startTime);
    }
}
