package task2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * Runner
 * Date: 02/20/2023
 *
 * @author Yauheni Antsipenka
 */
public final class Runner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);

    private Runner() {
    }

    public static void runQuickSortDefault(int[] arr) {
        LOGGER.info("Incoming array: {}", Arrays.toString(arr));
        long startTime = System.nanoTime();
        int[] sortedArr = QuickSortDefault.quickSort(arr, 0, arr.length - 1);
        long endTime = System.nanoTime();
        LOGGER.info("Sorted array: {}. Time to execute: {}", Arrays.toString(sortedArr), endTime - startTime);
    }

    public static void runQuickSortAsynchronously(int[] arr) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        LOGGER.info("Incoming array: {}", Arrays.toString(arr));
        long startTime = System.nanoTime();
        int[] sortedArr = forkJoinPool.submit(new QuickSortRecursiveTask(arr, 0, arr.length - 1)).get();
        long endTime = System.nanoTime();
        LOGGER.info("Sorted array: {}. Time to execute: {}", Arrays.toString(sortedArr), endTime - startTime);
    }
}
