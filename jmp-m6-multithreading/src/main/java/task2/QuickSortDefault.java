package task2;

/**
 * QuickSortDefault
 * Date: 02/04/2023
 *
 * @author Yauheni Antsipenka
 */
public class QuickSortDefault {

    public static int[] quickSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
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
