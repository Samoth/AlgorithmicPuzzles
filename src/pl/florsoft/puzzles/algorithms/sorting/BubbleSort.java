package pl.florsoft.puzzles.algorithms.sorting;

/**
 * Bubble sort algorithm.
 * Time complexity: O(n*n).
 * Memory complexity: O(1).
 */
public class BubbleSort {

    /**
     * Sort array of ints using bubble sort algorithm.
     */
    public static void sort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * Sort array of longs using bubble sort algorithm.
     */
    public static void sort(long[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    long tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }

}
