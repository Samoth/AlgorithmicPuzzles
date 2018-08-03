package pl.florsoft.other.findduplicate;

/**
 * Task: given A array consisting N+1 integers within the range [0..N-1]. So there is at least one duplicate.
 * Find duplicate number, using O(1) memory.
 */
public class FindDuplicateInArray {

    /**
     * Find a cycle in a graph.
     */
    public static int findDuplicate(int[] A) {
        int startElem = A[A.length - 1];
        if (A[startElem] == startElem) {
            return startElem;
        }
        int slow = startElem, fast = startElem;
        do {
            slow = getNextVal(A, slow);
            fast = getNextVal(A, getNextVal(A, fast));
        } while (slow != fast);
        slow = startElem;
        while (slow != fast) {
            slow = getNextVal(A, slow);
            fast = getNextVal(A, fast);
        }
        return fast;
    }

    private static int getNextVal(int[] A, int index) {
        return A[index];
    }

    /**
     * Find duplicate elements. Time complexity: O(n*logn).
     */
    public static int findDuplicateDivideAndConquer(int[] A) {
        if (A.length < 2) {
            throw new IllegalArgumentException("Incorrect array");
        }
        return findDuplicateLogN(A, 0, A.length - 2);
    }

    private static int findDuplicateLogN(int[] A, int min, int max) {
        if (min == max) {
            return min;
        } else if (min > max) {
            throw new IllegalArgumentException("Incorrect array. No duplicates.");
        }
        int sum = 0, elems = 0;
        for (int val : A) {
            if (val >= min && val <= max) {
                sum += val;
                elems++;
            }
        }
        double avg = (double) sum / elems;
        double middleElem = (double) (min + max) / 2;
        if (avg <= middleElem) {
            return findDuplicateLogN(A, min, (int) middleElem);
        } else {
            return findDuplicateLogN(A, (int) middleElem + 1, max);
        }
    }

    private static int findDuplicateUsingCount(int[] A, int min, int max) {
        if (min == max) {
            return min;
        } else if (min > max) {
            throw new IllegalArgumentException("Incorrect array. No duplicates.");
        }
        int left = 0, right = 0;
        int middleElem = (min + max) / 2;
        for (int val : A) {
            if (val >= min && val <= max) {
                if (val > middleElem) {
                    right++;
                } else {
                    left++;
                }
            }
        }
        if (left >= right) {
            return findDuplicateUsingCount(A, min, middleElem);
        } else {
            return findDuplicateUsingCount(A, middleElem + 1, max);
        }
    }

    /**
     * Only one duplicate exists.
     */
    public static int findOneDuplicate(int[] A) {
        if (A.length < 2) {
            throw new IllegalArgumentException("Incorrect array");
        }
        long sumWithoutDup = (long) (A.length - 2) * (A.length - 1) / 2;
        long currentSum = 0;
        for (int val : A) {
            currentSum += val;
        }
        return (int) (currentSum - sumWithoutDup);
    }

    /**
     * Many duplicates exists. Only for small array size (less or equals 65)
     */
    public static int findDuplicateForSmallArray(int[] A) {
        if (A.length < 2 || A.length > Long.SIZE + 1) {
            throw new IllegalArgumentException("Incorrect array");
        }
        long numbers = 0;
        for (int val : A) {
            long mask = 1L << val;
            if ((numbers & mask) == 0L) {
                numbers |= mask;
            } else {
                return val;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] testArray = new int[]{1, 1, 0};
        System.out.println("1 ?= " + findDuplicateDivideAndConquer(testArray));
        int[] testArray2 = new int[]{0, 1, 2, 3, 0};
        System.out.println("0 ?= " + findDuplicateDivideAndConquer(testArray2));
        int[] testArray3 = new int[]{5, 3, 2, 5, 1, 4, 0};
        System.out.println("5 ?= " + findDuplicateDivideAndConquer(testArray3));
        int[] testArray4 = new int[]{6, 1, 4, 2, 2, 3, 0, 5};
        System.out.println("2 ?= " + findDuplicateDivideAndConquer(testArray4));
        int[] testArray5 = new int[]{2, 1, 3, 0, 0, 0, 2};
        System.out.println("0 ?= " + findDuplicateDivideAndConquer(testArray5));
    }

}
