package pl.florsoft.puzzles.codility;

import java.util.Arrays;

/**
 * Task: https://app.codility.com/programmers/lessons/6-sorting/triangle/
 */
public class L06Triangle {

    public int solution(int[] A) {
        if (A.length < 3) {
            return 0;
        }
        Arrays.sort(A);
        for (int i = 0; i < A.length - 2; i++) {
            if (A[i] >= 0 && (long) A[i] + A[i + 1] > A[i + 2]) {
                return 1;
            }
        }
        return 0;
    }

}
