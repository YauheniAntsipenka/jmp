package task6.sum_of_doubles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ForkJoinPool;

/**
 * Main
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        double[] array = Initializer.getArray();

        long startTime = System.nanoTime();
        LOGGER.info("sum: {}", sumOfSquares(new ForkJoinPool(), array));
        long endTime = System.nanoTime();
        LOGGER.info("Time to execute(parallel): {}", endTime - startTime);

        startTime = System.nanoTime();
        LOGGER.info("sum: {}", sumOfSquares(array));
        endTime = System.nanoTime();
        LOGGER.info("Time to execute(linear): {}", endTime - startTime);
    }

    static double sumOfSquares(ForkJoinPool pool, double[] array) {
        int n = array.length;
        Applyer a = new Applyer(array, 0, n, null);
        pool.invoke(a);
        return a.result;
    }

    static  double sumOfSquares(double[] arr) {
        double sum = 0;
        for (double v : arr) {
            sum += v;
        }
        return sum;
    }
}
