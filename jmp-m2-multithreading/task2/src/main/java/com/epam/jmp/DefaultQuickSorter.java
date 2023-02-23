package com.epam.jmp;

import com.epam.jmp.services.api.SortUtils;
import com.epam.jmp.services.impl.QuickSortUtils;

/**
 * QuickSortDefault
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
