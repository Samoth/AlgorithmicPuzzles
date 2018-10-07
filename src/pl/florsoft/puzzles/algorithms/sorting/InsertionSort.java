package pl.florsoft.puzzles.algorithms.sorting;

/**
 * Insertion sort algorithm.
 * Time complexity: O(n*n).
 * Memory complexity: O(1).
 */
public class InsertionSort {

    /**
     * Sort array of ints using insertion sort algorithm.
     */
    public static void sort(int[] array) {
        sort(array, 0, array.length);
    }

    /**
     * @param startIdx first index to sort - inclusive
     * @param endIdx   last index to sort - exclusive
     */
    public static void sort(int[] array, int startIdx, int endIdx) {
        if (array == null || endIdx - startIdx <= 1) {
            return;
        }
        for (int i = startIdx + 1; i < endIdx; i++) {
            int currentElem = array[i];
            int j = i - 1;
            while (j >= startIdx && currentElem < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = currentElem;
        }
    }

    /**
     * Sort array of longs using insertion sort algorithm.
     */
    public static void sort(long[] array) {
        sort(array, 0, array.length);
    }

    /**
     * @param startIdx first index to sort - inclusive
     * @param endIdx   last index to sort - exclusive
     */
    public static void sort(long[] array, int startIdx, int endIdx) {
        if (array == null || endIdx - startIdx <= 1) {
            return;
        }
        for (int i = startIdx + 1; i < endIdx; i++) {
            long currentElem = array[i];
            int j = i - 1;
            while (j >= startIdx && currentElem < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = currentElem;
        }
    }

}
