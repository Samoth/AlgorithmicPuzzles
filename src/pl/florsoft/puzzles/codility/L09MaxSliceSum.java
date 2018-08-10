package pl.florsoft.puzzles.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/9-maximum_slice_problem/max_slice_sum/
 */
public class L09MaxSliceSum {

    public int solution(int[] A) {
        int maxSum = A[0], currentSum = A[0];
        for (int i = 1; i < A.length; i++) {
            if (currentSum + A[i] < A[i]) {
                currentSum = A[i];
            } else {
                currentSum += A[i];
            }
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
    
}
