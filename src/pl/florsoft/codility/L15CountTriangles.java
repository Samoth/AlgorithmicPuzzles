package pl.florsoft.codility;

import java.util.Arrays;

/**
 * Task: https://app.codility.com/programmers/lessons/15-caterpillar_method/count_triangles/
 */
public class L15CountTriangles {

    public int solution(int[] A) {
        if (A.length < 3) {
            return 0;
        }
        Arrays.sort(A);
        int count = 0, z;
        for (int x = 0; x < A.length - 2; x++) {
            z = x + 2;
            for (int y = x + 1; y < A.length - 1; y++) {
                while (z < A.length && A[x] + A[y] > A[z]) {
                    z++;
                }
                count += z - y - 1;
            }
        }
        return count;
    }
}
