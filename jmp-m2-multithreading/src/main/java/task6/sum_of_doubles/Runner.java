package task6.sum_of_doubles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ForkJoinPool;

/**
 * Runner
 * Date: 02/20/2023
 *
 * @author Yauheni Antsipenka
 */
public final class Runner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);

    private Runner() {};

    public static void calculateSumOfSquares(double[] array) {
        long startTime = System.nanoTime();
        double sum = sumOfSquares(array);
        long endTime = System.nanoTime();
        LOGGER.info("sum: {}. Time to execute(linear): {}", sum, endTime - startTime);
    }

    public static void calculateSumOfSquaresAsynchronously(double[] array) {
        long startTime = System.nanoTime();
        double sum = sumOfSquares(new ForkJoinPool(), array);
        long endTime = System.nanoTime();
        LOGGER.info("sum: {}. Time to execute(parallel): {}", sum, endTime - startTime);
    }

    private static double sumOfSquares(ForkJoinPool pool, double[] array) {
        int n = array.length;
        Applyer a = new Applyer(array, 0, n, null);
        pool.invoke(a);
        return a.result;
    }

    private static  double sumOfSquares(double[] arr) {
        double sum = 0;
        for (double v : arr) {
            sum += v;
        }
        return sum;
    }
}
