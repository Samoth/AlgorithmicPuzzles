package pl.florsoft.puzzles.codility;

import java.util.HashSet;
import java.util.Set;

/**
 * https://app.codility.com/programmers/lessons/4-counting_elements/missing_integer/
 */
public class L04MissingInteger {

    public int solution(int[] A) {
        Set<Integer> positiveNumbers = new HashSet<>();
        for (int val: A) {
            if (val > 0) {
                positiveNumbers.add(val);
            }
        }
        for (int i = 1; i <= A.length; i++) {
            if (!positiveNumbers.contains(i)) {
                return i;
            }
        }
        return A.length + 1;
    }

}
