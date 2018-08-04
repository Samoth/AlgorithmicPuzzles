package pl.florsoft.puzzles.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/9-maximum_slice_problem/max_profit/
 */
public class L09MaxProfit {

    public int solution(int[] A) {
        if (A == null || A.length <= 1) {
            return 0;
        }
        int maxProfit = 0, actualProfit = 0, minValue = A[0];
        for (int i = 1; i < A.length; i++) {
            actualProfit = Math.max(0, A[i] - minValue);
            maxProfit = Math.max(maxProfit, actualProfit);
            if (A[i] < minValue) {
                minValue = A[i];
            }
        }
        return maxProfit;
    }
}
