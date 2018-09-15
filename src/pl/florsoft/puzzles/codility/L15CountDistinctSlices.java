package pl.florsoft.puzzles.codility;

import java.util.HashSet;
import java.util.Set;

/**
 * Task: https://app.codility.com/programmers/lessons/15-caterpillar_method/count_distinct_slices/
 */
public class L15CountDistinctSlices {

    public int solution(int M, int[] A) {
        int distinctSlices = 0;
        int rightIdx = 0;
        Set<Integer> currentNumbers = new HashSet<>();
        for (int leftIdx = 0; leftIdx < A.length; leftIdx++) {
            while (rightIdx < A.length && !currentNumbers.contains(A[rightIdx])) {
                currentNumbers.add(A[rightIdx]);
                rightIdx++;
            }
            distinctSlices += currentNumbers.size();
            if (distinctSlices > 1000000000) {
                return 1000000000;
            }
            currentNumbers.remove(A[leftIdx]);
        }
        return distinctSlices;
    }

}