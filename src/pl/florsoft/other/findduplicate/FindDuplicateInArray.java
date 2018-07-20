package pl.florsoft.other.findduplicate;

/**
 * Task: given A array consisting N+1 integers within the range [0..N-1]. So there is at least one duplicate.
 * Find duplicate number.
 */
public class FindDuplicateInArray {

    /**
     * Only one duplicate exists.
     */
    public static int findOneDuplicate(int[] A) {
        if (A.length < 2) {
            throw new IllegalArgumentException("Incorrect array");
        }
        long sumWithoutDup = (long) (A.length - 2) * (A.length - 1) / 2;
        long currentSum = 0;
        for (int val: A) {
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
        for (int val: A) {
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
        int[] testArray = new int[]{1, 1};
        System.out.println("1 ?= " + findDuplicateForSmallArray(testArray));
        int[] testArray2 = new int[]{0, 1, 2, 3, 0};
        System.out.println("0 ?= " + findDuplicateForSmallArray(testArray2));
        int[] testArray3 = new int[]{5, 3, 2, 4, 1, 4, 0};
        System.out.println("4 ?= " + findDuplicateForSmallArray(testArray3));
        int[] testArray4 = new int[65];
        for (int i = 0; i < testArray4.length - 1; i++) {
            testArray4[i] = i;
        }
        testArray4[64] = 56;
        System.out.println("56 ?= " + findDuplicateForSmallArray(testArray4));
    }

}
