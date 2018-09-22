package pl.florsoft.puzzles.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/5-prefix_sums/min_avg_two_slice/
 */
public class L05MinAvgTwoSlice {

    public int solution(int[] A) {
        int[][] minSum = new int[A.length][2];
        minSum[0][0] = A[0];
        minSum[0][1] = 1;
        for (int i = 1; i < A.length; i++) {
            if (getCurrentSum(i, minSum, A) < getLastTwoElemsSum(i, A)) {
                minSum[i][0] = minSum[i - 1][0] + A[i];
                minSum[i][1] = minSum[i - 1][1] + 1;
            } else {
                minSum[i][0] = A[i - 1] + A[i];
                minSum[i][1] = 2;
            }
        }
        double minAvg = Double.MAX_VALUE;
        int minIdx = 0;
        for (int i = 1; i < A.length; i++) {
            double currentAvg = ((double) minSum[i][0] / minSum[i][1]);
            if (currentAvg < minAvg) {
                minAvg = currentAvg;
                minIdx = i - minSum[i][1] + 1;
            }
        }
        return minIdx;
    }

    private double getCurrentSum(int i, int[][] minSum, int[] A) {
        return ((double) minSum[i - 1][0] + A[i]) / (minSum[i - 1][1] + 1);
    }

    private double getLastTwoElemsSum(int i, int[] A) {
        return ((double) A[i - 1] + A[i]) / 2;
    }

}
