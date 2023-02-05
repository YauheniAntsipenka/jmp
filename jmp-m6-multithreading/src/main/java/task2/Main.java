package task2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * Main
 * Date: 02/04/2023
 *
 * @author Yauheni Antsipenka
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] arr1 = Arrays.copyOf(Initializer.arr, Initializer.arr.length);
        LOGGER.info(Arrays.toString(arr1));
        long startTime = System.nanoTime();
        int[] sortedArr = QuickSortDefault.quickSort(arr1, 0, arr1.length - 1);
        long endTime = System.nanoTime();
        LOGGER.info(Arrays.toString(sortedArr));
        LOGGER.info("Time to execute: {}", endTime - startTime);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int[] arr2 = Arrays.copyOf(Initializer.arr, Initializer.arr.length);
        LOGGER.info(Arrays.toString(arr2));
        startTime = System.nanoTime();
        ForkJoinTask<int[]> quickSort = forkJoinPool.submit(new QuickSortRecursiveTask(arr2, 0, arr2.length - 1));
        sortedArr = quickSort.get();
        endTime = System.nanoTime();
        LOGGER.info(Arrays.toString(sortedArr));
        LOGGER.info("Time to execute: {}", endTime - startTime);
    }
}
