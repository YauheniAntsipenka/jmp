package com.epam.jmp.sum_of_doubles;

import com.epam.jmp.sum_of_doubles.services.impl.JMPInitializerImpl;

/**
 * Main
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class Main {

    public static void main(String[] args) {
        double[] array = new JMPInitializerImpl().retrieveArray();
        SumOfSquaresCalculator.calculate(array);
        SumOfSquaresCalculator.calculateAsynchronously(array);
    }
}
