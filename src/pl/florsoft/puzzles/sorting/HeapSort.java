package pl.florsoft.puzzles.sorting;

import java.util.Arrays;

public class HeapSort {

    /**
     * Sort array of longs using heap sort algorithm.
     * Time complexity: O(n*logn).
     * Memory complexity: O(1).
     */
    public static void sort(long[] array) {
        sort(array, 0, array.length);
    }

    /**
     * @param fromIndex first index to sort - inclusive
     * @param toIndex   last index to sort - exclusive
     */
    public static void sort(long[] array, int fromIndex, int toIndex) {
        if (array.length <= 1) {
            return;
        }
        buildHeap(array); // TODO add range: from, to
        sortHeap(array);// TODO add range: from, to
    }

    private static void buildHeap(long[] array) {
        for (int i = 0; i < array.length; i++) {
            shiftUp(array, i);
        }
    }

    private static void shiftUp(long[] array, int idx) {
        if (idx == 0) {
            return;
        }
        int parent = getParent(idx);
        if (array[idx] > array[parent]) {
            swap(array, idx, parent);
            shiftUp(array, parent);
        }
    }

    private static void sortHeap(long[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            swap(array, 0, i);
            shiftDown(array, 0, i);
        }
    }

    private static void shiftDown(long[] array, int idx, int lastIdx) {
        int leftChild = getLeftChild(idx);
        int rightChild = getRightChild(idx);
        if (leftChild >= lastIdx) {
            return;
        }
        int maxIdx = leftChild;
        if (rightChild < lastIdx) {
            maxIdx = array[leftChild] >= array[rightChild] ? leftChild : rightChild;
        }
        if (array[idx] < array[maxIdx]) {
            swap(array, idx, maxIdx);
            shiftDown(array, maxIdx, lastIdx);
        }
    }

    private static int getParent(int idx) {
        return (int) Math.ceil((double) idx / 2) - 1;
    }

    private static int getLeftChild(int idx) {
        return idx * 2 + 1;
    }

    private static int getRightChild(int idx) {
        return idx * 2 + 2;
    }

    private static void swap(long[] array, int idx1, int idx2) {
        long tmp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = tmp;
    }

    public static void main(String[] args) {
        long[] arr = new long[]{10, 4, 8, 5, 12, 2, 6, 11, 3, 9, 7, 1};
        System.out.println("array before sort: " + Arrays.toString(arr));
        sort(arr);
        System.out.println("array after sort: " + Arrays.toString(arr));
    }

}
