package pl.florsoft.puzzles.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/9-maximum_slice_problem/max_double_slice_sum/
 */
public class L09MaxDoubleSliceSum {

    public int solution(int[] A) {
        int currentSum = 0, maxDoubleSlice = 0, minVal = A[1], prevSum = 0;
        if (A.length == 3) {
            return maxDoubleSlice;
        }
        for (int i = 1; i < A.length - 1; i++) {
            int val = A[i];
            if (val < minVal) {
                minVal = val;
            }
            if (val >= 0) {
                currentSum += val;
            } else {
                if (currentSum + val - prevSum >= currentSum) {
                    currentSum = currentSum + val - prevSum;
                    prevSum = currentSum;
                    minVal = val;
                } else {
                    if (currentSum - minVal + val > 0) {
                        currentSum += val;
                    } else {
                        currentSum = val;
                        minVal = val;
                        prevSum = val;
                    }
                }
            }
            maxDoubleSlice = Math.max(maxDoubleSlice, currentSum - minVal);
        }
        return maxDoubleSlice;
    }

    public static void main(String[] args) {
        // test cases
        int[] test1 = {2, 3, -1231, 1, 3, -10000, 3, 1, 2, -111, 4, 3}; // 10
        int[] test2 = {-2, -3, -4, 1, -5, -6, -7}; // 1
        int[] test3 = {5, 17, 0, 3}; // 17
        int[] test4 = {5, 1, -7, 1, -8, 3, -2, 8, -8, 20, 8}; // 29
        int[] test5 = {-4, -5, -1, -5, -7, -19, -11}; // 0
        int[] test6 = {3, 2, 6, -1, 4, 5, -2, 20, 2}; // 36
    }

}
