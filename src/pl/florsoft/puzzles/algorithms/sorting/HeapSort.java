package pl.florsoft.puzzles.algorithms.sorting;

/**
 * Heap sort algorithm.
 * Time complexity: O(n*logn).
 * Memory complexity: O(1).
 */
public class HeapSort {

    /**
     * Sort array of longs using heap sort algorithm.
     */
    public static void sort(long[] array) {
        sort(array, 0, array.length);
    }

    /**
     * @param fromIndex first index to sort - inclusive
     * @param toIndex   last index to sort - exclusive
     */
    public static void sort(long[] array, int fromIndex, int toIndex) {
        if (fromIndex > toIndex || toIndex > array.length || fromIndex < 0) {
            throw new IllegalArgumentException();
        } else if (toIndex - fromIndex <= 1) {
            return;
        }
        buildHeap(array, fromIndex, toIndex);
        sortHeap(array, fromIndex, toIndex);
    }

    private static void buildHeap(long[] array, int fromIndex, int toIndex) {
        for (int i = 0; i < toIndex - fromIndex; i++) {
            shiftUp(array, i, fromIndex);
        }
    }

    private static void shiftUp(long[] array, int idx, int prefix) {
        if (idx == 0) {
            return;
        }
        int parent = getParent(idx);
        if (array[idx + prefix] > array[parent + prefix]) {
            swap(array, idx + prefix, parent + prefix);
            shiftUp(array, parent, prefix);
        }
    }

    private static void sortHeap(long[] array, int fromIndex, int toIndex) {
        for (int i = toIndex - fromIndex - 1; i > 0; i--) {
            swap(array, fromIndex, i + fromIndex);
            shiftDown(array, 0, i, fromIndex);
        }
    }

    private static void shiftDown(long[] array, int idx, int lastIdx, int prefix) {
        int leftChild = getLeftChild(idx);
        int rightChild = getRightChild(idx);
        if (leftChild >= lastIdx) {
            return;
        }
        int maxIdx = leftChild;
        if (rightChild < lastIdx) {
            maxIdx = array[leftChild + prefix] >= array[rightChild + prefix] ? leftChild : rightChild;
        }
        if (array[idx + prefix] < array[maxIdx + prefix]) {
            swap(array, idx + prefix, maxIdx + prefix);
            shiftDown(array, maxIdx, lastIdx, prefix);
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

}
