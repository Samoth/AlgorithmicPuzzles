package pl.florsoft.puzzles.codility;

import java.util.Arrays;

/**
 * Task: https://app.codility.com/programmers/lessons/6-sorting/distinct/
 */
public class L06Distinct {

    public int solution(int[] A) {
        int distinctVals = 0, prevVal = Integer.MAX_VALUE;
        Arrays.sort(A);
        for (int i = 0; i < A.length; i++) {
            if (A[i] != prevVal) {
                distinctVals++;
            }
            prevVal = A[i];
        }
        return distinctVals;
    }

}
