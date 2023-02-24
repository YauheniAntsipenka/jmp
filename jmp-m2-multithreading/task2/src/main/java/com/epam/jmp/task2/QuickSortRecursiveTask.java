package com.epam.jmp.task2;

import com.epam.jmp.task4.services.api.SortUtils;
import com.epam.jmp.task4.services.impl.QuickSortUtils;

import java.util.concurrent.RecursiveTask;

/**
 * QuickSortRecursiveTask
 * Date: 02/04/2023
 *
 * @author Yauheni Antsipenka
 */
public class QuickSortRecursiveTask extends RecursiveTask<int[]> {

    private final int[] arr;
    private final int begin;
    private final int end;
    private final SortUtils sortUtils = new QuickSortUtils();

    public QuickSortRecursiveTask(int[] arr, int begin, int end) {
        this.arr = arr;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public int[] compute() {
        if (begin < end) {
            int partitionIndex = sortUtils.partition(arr, begin, end);

            QuickSortRecursiveTask leftArr = new QuickSortRecursiveTask(arr, begin, partitionIndex - 1);
            leftArr.fork().join();

            QuickSortRecursiveTask rightArr = new QuickSortRecursiveTask(arr, partitionIndex + 1, end);
            rightArr.fork().join();
        }
        return arr;
    }
}
