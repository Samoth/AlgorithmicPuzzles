package pl.florsoft.puzzles.codility;

import java.util.Arrays;

/**
 * Task: https://app.codility.com/programmers/lessons/6-sorting/number_of_disc_intersections/
 */
public class L06NumberOfDiscIntersections {

    public int solution(int[] A) {
        int[] discsSt = new int[A.length];
        int[] discsEn = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            discsSt[i] = Math.max(0, i - A[i]);
            discsEn[i] = (int) Math.min((long) A.length - 1, ((long) i + (long) A[i]));
        }
        Arrays.sort(discsSt);
        Arrays.sort(discsEn);
        return countElements(discsSt, discsEn);
    }

    private static int countElements(int[] discsSt, int[] discsEn) {
        int count = 0, currentItems = 0, endIdx = 0;
        for (int disc : discsSt) {
            while (disc > discsEn[endIdx]) {
                endIdx++;
                currentItems--;
                count += currentItems;
                if (count > 10000000) {
                    return -1;
                }
            }
            currentItems++;
        }
        while (currentItems != 0) {
            currentItems--;
            count += currentItems;
            if (count > 10000000) {
                return -1;
            }
        }
        return count;
    }

}
