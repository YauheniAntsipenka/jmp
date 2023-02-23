package com.epam.jmp.sum_of_doubles.services.impl;

import com.epam.jmp.sum_of_doubles.services.api.JMPInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * JMPInitializer
 * Date: 02/04/2023
 *
 * @author Yauheni Antsipenka
 */
public class JMPInitializerImpl implements JMPInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JMPInitializer.class);

    @Override
    public double[] retrieveArray() {
        double[] arr = new double[500_000_000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextDouble();
        }
        LOGGER.info("arr: {}", arr);
        return arr;
    }
}
