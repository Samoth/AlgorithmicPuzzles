package pl.florsoft.puzzles.algorithms.searching;

/**
 * Binary search algorithm.
 * Time complexity: O(logn).
 * Memory complexity: O(1).
 */
public class BinarySearch {

    /**
     * Find index of target value in sorted array, using binary search algorithm. Recursive implementation.
     *
     * @param sortedArray
     * @param value
     * @return the index of target value or {@code -1} if the value does not occur.
     */
    public static int find(int[] sortedArray, int value) {
        if (sortedArray == null || sortedArray.length == 0) {
            return -1;
        }
        return find(sortedArray, value, 0, sortedArray.length);
    }

    /**
     * @param fromIndex first index - inclusive
     * @param toIndex   last index - exclusive
     */
    private static int find(int[] sortedArray, int value, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            return -1;
        }
        int middleIdx = (toIndex - fromIndex) / 2 + fromIndex;
        if (sortedArray[middleIdx] == value) {
            return middleIdx;
        } else if (sortedArray[middleIdx] > value) {
            return find(sortedArray, value, fromIndex, middleIdx - 1);
        } else {
            return find(sortedArray, value, middleIdx + 1, toIndex);
        }
    }

    /**
     * Find index of target value in sorted array, using binary search algorithm. Iterative implementation.
     *
     * @param sortedArray
     * @param value
     * @return the index of target value or {@code -1} if the value does not occur.
     */
    public static int findIter(int[] sortedArray, int value) {
        if (sortedArray == null || sortedArray.length == 0) {
            return -1;
        }
        int left = 0;
        int right = sortedArray.length - 1;
        while (left <= right) {
            int middle = (right + 1 - left) / 2 + left;
            if (sortedArray[middle] == value) {
                return middle;
            } else if (sortedArray[middle] > value) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }

}
