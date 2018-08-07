package pl.florsoft.puzzles.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/9-maximum_slice_problem/max_double_slice_sum/
 */
public class L09MaxDoubleSliceSum {

    public int solution(int[] A) {
        int currentSum = 0, maxDoubleSlice = 0, minVal = A[1], prevSum = 0;
        for (int i = 1; i < A.length - 1; i++) {
            int val = A[i];
            if (currentSum + val - minVal >= 0) {
                currentSum += val;
            } else {
                currentSum = currentSum + val - prevSum;
                prevSum = currentSum;
                //currentSum = 0;
                minVal = val;
            }
            if (val < minVal) {
                minVal = val;
                // prevSum = currentSum;
            }
            maxDoubleSlice = Math.max(maxDoubleSlice, currentSum - minVal);
            // currentSum = Math.max(0, currentSum + val);

            System.out.println("val = " + val + ", minVal = " + minVal + ", prevSum = " + prevSum + ", currentSum = " + currentSum + ", max = " + maxDoubleSlice);

        }
        return maxDoubleSlice;
    }

    public static void main(String[] args) {
        int[] test1 = {2, 3, -1231, 1, 3, -10000, 3, 1, 2, -111, 4, 3};
        int[] test2 = {-2, -3, -4, 1, -5, -6, -7};
        int[] test3 = {5, 17, 0, 3};
    }

}
