package pl.florsoft.puzzles.algorithms.sorting;

import java.util.concurrent.RecursiveTask;

/**
 * Parallel merge sort algorithm.
 * Time complexity: O(n*logn).
 * Memory complexity: O(n).
 */
public class ParallelMergeSort {

    /**
     * Sort array of ints using parallel merge sort algorithm.
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
        SortIntRecursiveTask taskOne = new SortIntRecursiveTask(target, source, start, middle);
        SortIntRecursiveTask taskTwo = new SortIntRecursiveTask(target, source, middle, end);
        taskOne.fork();
        taskTwo.fork();
        System.arraycopy(taskOne.join(), start, source, start, middle - start);
        System.arraycopy(taskTwo.join(), middle, source, middle, end - middle);
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

    static class SortIntRecursiveTask extends RecursiveTask<int[]> {

        private static final int THRESHOLD = 128;
        private int[] source;
        private int[] target;
        private int start;
        private int end;

        public SortIntRecursiveTask(int[] source, int[] target, int start, int end) {
            this.source = source;
            this.target = target;
            this.start = start;
            this.end = end;
        }

        @Override
        protected int[] compute() {
            if (end - start < THRESHOLD) {
                InsertionSort.sort(source, start, end);
                return source;
            }
            sort(source, target, start, end);
            return target;
        }

    }

    /**
     * Sort array of longs using parallel merge sort algorithm.
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
        SortRecursiveTask taskOne = new SortRecursiveTask(target, source, start, middle);
        SortRecursiveTask taskTwo = new SortRecursiveTask(target, source, middle, end);
        taskOne.fork();
        taskTwo.fork();
        System.arraycopy(taskOne.join(), start, source, start, middle - start);
        System.arraycopy(taskTwo.join(), middle, source, middle, end - middle);
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

    static class SortRecursiveTask extends RecursiveTask<long[]> {

        private static final int THRESHOLD = 128;
        private long[] source;
        private long[] target;
        private int start;
        private int end;

        public SortRecursiveTask(long[] source, long[] target, int start, int end) {
            this.source = source;
            this.target = target;
            this.start = start;
            this.end = end;
        }

        @Override
        protected long[] compute() {
            if (end - start < THRESHOLD) {
                InsertionSort.sort(source, start, end);
                return source;
            }
            sort(source, target, start, end);
            return target;
        }

    }

}
