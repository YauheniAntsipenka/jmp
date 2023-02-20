package task6.sum_of_doubles.services.impl;

import task6.sum_of_doubles.services.api.InitService;

import java.util.Random;

/**
 * Initializer
 * Date: 02/04/2023
 *
 * @author Yauheni Antsipenka
 */
public class InitServiceImpl implements InitService {

    @Override
    public double[] retrieveArray() {
        double[] arr = new double[500_000_000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextDouble();
        }
        return arr;
    }
}
