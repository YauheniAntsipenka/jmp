package task6.sum_of_doubles;

import java.util.Random;

/**
 * Initializer
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class Initializer {

    public static double[] getArray() {
        double[] arr = new double[500_000_000];
        for (int i = 0; i < 500_000_000; i++) {
            arr[i] = new Random().nextDouble();
        }
        return arr;
    }
}
