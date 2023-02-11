package task6;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import task6.Fibonacci.FibonacciRecursiveTask;

import java.util.concurrent.ForkJoinPool;

/**
 * FibonacciTest
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class FibonacciTest {

    @Test
    public void test() {
        assertEquals(1134903170L, new ForkJoinPool().invoke(new FibonacciRecursiveTask(45)).longValue());
    }
}
