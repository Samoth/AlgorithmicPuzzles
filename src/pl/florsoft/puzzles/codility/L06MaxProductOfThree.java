package pl.florsoft.puzzles.codility;

import java.util.Arrays;

/**
 * Task: https://app.codility.com/programmers/lessons/6-sorting/max_product_of_three/
 */
public class L06MaxProductOfThree {

    public int solution(int[] A) {
        if (A.length == 3) {
            return A[0] * A[1] * A[2];
        }
        int length = A.length;
        Arrays.sort(A);
        return Math.max(A[length - 3] * A[length - 2] * A[length - 1], A[0] * A[1] * A[length - 1]);
    }
}
