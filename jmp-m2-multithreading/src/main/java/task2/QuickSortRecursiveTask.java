package task2;

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

    public QuickSortRecursiveTask(int[] arr, int begin, int end) {
        this.arr = arr;
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected int[] compute() {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            QuickSortRecursiveTask leftArr = new QuickSortRecursiveTask(arr, begin, partitionIndex - 1);
            leftArr.fork().join();
            QuickSortRecursiveTask rightArr = new QuickSortRecursiveTask(arr, partitionIndex + 1, end);
            rightArr.fork().join();
        }
        return arr;
    }

    private static int partition(int[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = begin - 1;
        for (int j = begin; j < end; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, end);
        return i + 1;
    }

    private static void swap(int[] arr, int from, int to) {
        int swapTemp = arr[from];
        arr[from] = arr[to];
        arr[to] = swapTemp;
    }
}
