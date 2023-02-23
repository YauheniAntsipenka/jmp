package com.epam.jmp;

import java.util.concurrent.ExecutionException;

/**
 * Main
 * Date: 02/04/2023
 *
 * @author Yauheni Antsipenka
 */
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int number = 10;
        FactorialCalculator.calculate(number);
        FactorialCalculator.calculateAsynchronously(number);
    }
}
