package task1;

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

    private Runner() {
    }

    public static void calculateDefaultFactorial(int number) {
        long startTime = System.nanoTime();
        BigInteger result = FactorialDefault.get(number);
        long endTime = System.nanoTime();
        LOGGER.info("Factorial of {} is: {} Time to execute: {}", number, result, endTime - startTime);
    }

    public static void calculateFactorialAsynchronously(int number) throws ExecutionException, InterruptedException {
        long startTime = System.nanoTime();
        BigInteger result = new ForkJoinPool().submit(new FactorialRecursiveTask(BigInteger.valueOf(number))).get();
        long endTime = System.nanoTime();
        LOGGER.info("Factorial of {} is: {} Time to execute: {}", number, result, endTime - startTime);
    }
}
