package com.epam.jmp.task6.fibonacci;

import java.util.concurrent.ExecutionException;

/**
 * Main
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int number = 45;
        FibonacciCalculator.calculate(number);
        FibonacciCalculator.calculateAsynchronously(number);
    }
}
