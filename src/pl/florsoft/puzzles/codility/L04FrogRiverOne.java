package pl.florsoft.puzzles.codility;

import java.util.HashSet;
import java.util.Set;

/**
 * https://app.codility.com/programmers/lessons/4-counting_elements/frog_river_one/
 */
public class L04FrogRiverOne {

    public int solution(int X, int[] A) {
        Set<Integer> currentValues = new HashSet<Integer>();
        for (int i = 0; i < A.length; i++) {
            currentValues.add(A[i]);
            if (currentValues.size() == X) {
                return i;
            }
        }
        return -1;
    }

}
