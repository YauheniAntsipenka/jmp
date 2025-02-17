package com.epam.jmp.task2;

import com.epam.jmp.task4.services.api.SortUtils;
import com.epam.jmp.task4.services.impl.QuickSortUtils;

/**
 * DefaultQuickSorter
 * Date: 02/04/2023
 *
 * @author Yauheni Antsipenka
 */
public class DefaultQuickSorter {

    private final SortUtils sortUtils = new QuickSortUtils();

    public int[] sort(int[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = sortUtils.partition(arr, begin, end);

            sort(arr, begin, partitionIndex - 1);
            sort(arr, partitionIndex + 1, end);
        }
        return arr;
    }
}
