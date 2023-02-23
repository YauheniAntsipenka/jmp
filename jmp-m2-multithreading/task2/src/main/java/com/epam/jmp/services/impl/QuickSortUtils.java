package com.epam.jmp.services.impl;

import com.epam.jmp.services.api.SortUtils;

/**
 * QuickSortUtils
 * Date: 02/23/2023
 *
 * @author Yauheni Antsipenka
 */
public class QuickSortUtils implements SortUtils {

    @Override
    public int partition(int[] arr, int begin, int end) {
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

    public void swap(int[] arr, int from, int to) {
        int swapTemp = arr[from];
        arr[from] = arr[to];
        arr[to] = swapTemp;
    }
}
