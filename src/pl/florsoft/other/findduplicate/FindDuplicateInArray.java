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

    public static void main(String[] args) {
        int[] testArray = new int[]{0, 0};
        System.out.println("0 ?= " + findOneDuplicate(testArray));
        int[] testArray2 = new int[]{0, 1, 2, 3, 0};
        System.out.println("0 ?= " + findOneDuplicate(testArray2));
        int[] testArray3 = new int[]{5, 3, 2, 5, 1, 4, 0};
        System.out.println("5 ?= " + findOneDuplicate(testArray3));
    }
    
}
