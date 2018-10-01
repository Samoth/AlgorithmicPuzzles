package pl.florsoft.puzzles.algorithms.sorting;

/**
 * Quick sort algorithm.
 * Time complexity: O(n*logn).
 * Memory complexity: O(logn).
 */
public class QuickSort {

    public static void sort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        sort(array, 0, array.length - 1);
    }

    /**
     * @param fromIndex first index to sort - inclusive
     * @param toIndex   last index to sort - inclusive
     */
    private static void sort(int[] array, int fromIndex, int toIndex) {
        if (fromIndex < toIndex) {
            int p = partition(array, fromIndex, toIndex);
            sort(array, fromIndex, p - 1);
            sort(array, p + 1, toIndex);
        }
    }

    private static int partition(int[] array, int fromIndex, int toIndex) {
        int middleValueIdx = toIndex;
        int endIdx;
        int startIdx = fromIndex;
        while (startIdx < middleValueIdx) {
            endIdx = middleValueIdx - 1;
            if (array[middleValueIdx] > array[endIdx]) {
                while (startIdx < endIdx && array[startIdx] < array[middleValueIdx]) {
                    startIdx++;
                }
                if (startIdx < endIdx) {
                    swap(array, startIdx, endIdx);
                    swap(array, middleValueIdx, endIdx);
                }
            } else {
                swap(array, middleValueIdx, endIdx);
            }
            middleValueIdx--;
        }
        return middleValueIdx;
    }

    private static void swap(int[] array, int firstIdx, int secIdx) {
        int tmp = array[firstIdx];
        array[firstIdx] = array[secIdx];
        array[secIdx] = tmp;
    }

}
