package com.epam.jmp;

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
public final class QuickSortRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuickSortRunner.class);

    private QuickSortRunner() {
    }

    public static void run(int[] arr) {
        LOGGER.info("Incoming array: {}", Arrays.toString(arr));
        QuickSortDefault quickSort = new QuickSortDefault();
        long startTime = System.nanoTime();
        int[] sortedArr = quickSort.sort(arr, 0, arr.length - 1);
        long endTime = System.nanoTime();
        LOGGER.info("Sorted array: {}. Time to execute: {}", Arrays.toString(sortedArr), endTime - startTime);
    }

    public static void runAsynchronously(int[] arr) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        LOGGER.info("Incoming array: {}", Arrays.toString(arr));
        long startTime = System.nanoTime();
        int[] sortedArr = forkJoinPool.submit(new QuickSortRecursiveTask(arr, 0, arr.length - 1)).get();
        long endTime = System.nanoTime();
        LOGGER.info("Sorted array: {}. Time to execute: {}", Arrays.toString(sortedArr), endTime - startTime);
    }
}
