package pl.florsoft.puzzles.algorithms.sorting;

/**
 * Merge sort algorithm.
 * Time complexity: O(n*logn).
 * Memory complexity: O(n).
 */
public class MergeSort {

    public static void sort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        int[] tmpArray = new int[array.length];
        System.arraycopy(array, 0, tmpArray, 0, array.length);
        sort(tmpArray, array, 0, array.length - 1);
    }

    /**
     * @param start first index to sort - inclusive
     * @param end   last index to sort - inclusive
     */
    private static void sort(int[] source, int[] target, int start, int end) {
        if (start == end) {
            target[start] = source[start];
        } else if (start < end) {
            int middle = (end - start) / 2 + start;
            sort(target, source, start, middle);
            sort(target, source, middle + 1, end);
            merge(source, target, start, middle, end);
        }
    }

    private static void merge(int[] source, int[] target, int start, int middle, int end) {
        int i = start;
        int j = middle + 1;
        while (i < middle + 1 || j < end + 1) {
            if (i >= middle + 1) {
                target[start++] = source[j++];
            } else if (j >= end + 1) {
                target[start++] = source[i++];
            } else {
                if (source[i] < source[j]) {
                    target[start++] = source[i++];
                } else {
                    target[start++] = source[j++];
                }
            }
        }
    }

}
