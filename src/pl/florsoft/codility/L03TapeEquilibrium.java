package pl.florsoft.codility;

/**
 * https://app.codility.com/programmers/lessons/3-time_complexity/tape_equilibrium/
 */
public class L03TapeEquilibrium {

    public int solution(int[] A) {
        int sum = 0, minDiff = Integer.MAX_VALUE, currentSum = 0;
        for (int elem : A) {
            sum += elem;
        }
        for (int i = 0; i < A.length - 1; i++) {
            currentSum += A[i];
            sum -= A[i];
            minDiff = Math.min(minDiff, Math.abs(currentSum - sum));
        }
        return minDiff;
    }

}
