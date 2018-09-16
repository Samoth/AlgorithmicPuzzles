package pl.florsoft.puzzles.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/4-counting_elements/max_counters/
 */
public class L04MaxCounters {

    public int[] solution(int N, int[] A) {
        int[] result = new int[N];
        int maxCount = 0;
        int actualMinValue = 0;
        for (int val : A) {
            if (val == N + 1) { // max counter
                actualMinValue = maxCount;
            } else { // increment counter
                if (result[val - 1] < actualMinValue) {
                    result[val - 1] = actualMinValue;
                }
                result[val - 1] = result[val - 1] + 1;
                if (result[val - 1] > maxCount) {
                    maxCount = result[val - 1];
                }
            }
        }
        for (int i = 0; i < result.length; i++) {
            if (result[i] < actualMinValue) {
                result[i] = actualMinValue;
            }
        }
        return result;
    }

}
