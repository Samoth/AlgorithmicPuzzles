package pl.florsoft.puzzles.algorithms.sorting;

/**
 * Merge sort algorithm.
 * Time complexity: O(n*logn).
 * Memory complexity: O(n).
 */
public class MergeSort {

    /**
     * Sort array of ints using merge sort algorithm.
     */
    public static void sort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        sort(new int[array.length], array, 0, array.length);
    }

    /**
     * @param start first index to sort - inclusive
     * @param end   last index to sort - exclusive
     */
    private static void sort(int[] source, int[] target, int start, int end) {
        if (end - start < 2) {
            return;
        }
        int middle = (start + end) / 2;
        sort(target, source, start, middle);
        sort(target, source, middle, end);
        merge(source, target, start, middle, end);
    }

    private static void merge(int[] source, int[] target, int start, int middle, int end) {
        int i = start;
        int j = middle;
        while (start < end) {
            if (i < middle && (j >= end || source[i] < source[j])) {
                target[start++] = source[i++];
            } else {
                target[start++] = source[j++];
            }
        }
    }

    /**
     * Sort array of longs using merge sort algorithm.
     */
    public static void sort(long[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        sort(new long[array.length], array, 0, array.length);
    }

    /**
     * @param start first index to sort - inclusive
     * @param end   last index to sort - exclusive
     */
    private static void sort(long[] source, long[] target, int start, int end) {
        if (end - start < 2) {
            return;
        }
        int middle = (start + end) / 2;
        sort(target, source, start, middle);
        sort(target, source, middle, end);
        merge(source, target, start, middle, end);
    }

    private static void merge(long[] source, long[] target, int start, int middle, int end) {
        int i = start;
        int j = middle;
        while (start < end) {
            if (i < middle && (j >= end || source[i] < source[j])) {
                target[start++] = source[i++];
            } else {
                target[start++] = source[j++];
            }
        }
    }

}
