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
        int count = 0, r;
        for (int l = 0; l < A.length - 2; l++) {
            r = l + 2;
            while (r < A.length && A[l] + A[l + 1] > A[r]) {
                r++;
            }
            r--;
            count += computeCombination(l, r);
            if (l + 1 < A.length - 2 && A[l] == A[l + 1]) {
                int y = r + 1;
                while (y < A.length && A[l] + A[r] > A[y]) {
                    y++;
                }
                count += computeCombination(r - 1, y - 1);
            }
        }
        return count;
    }

    private long computeCombination(int l, int r) {
        if (r - l == 1) {
            return 0;
        } else if (r - l == 2) {
            return 1;
        }
        int k = 2, n = r - l;
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    private long factorial(int number) {
        long result = 1;
        for (int i = 2; i <= number; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        L15CountTriangles task = new L15CountTriangles();
        int[] example = {3, 3, 5, 6};
        System.out.println(task.solution(example));
    }
}
