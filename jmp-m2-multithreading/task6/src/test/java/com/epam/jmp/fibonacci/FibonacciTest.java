package com.epam.jmp.fibonacci;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.epam.jmp.task6.fibonacci.FibonacciRecursiveTask;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

/**
 * FibonacciTest
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class FibonacciTest {

    @Test
    public void testCalculateFibonacciAsynchronously() {
        assertEquals(1134903170L, new ForkJoinPool().invoke(new FibonacciRecursiveTask(45)).longValue());
    }
}
