package pl.florsoft.puzzles.codility;

import java.util.HashMap;
import java.util.Map;

/**
 * Task: https://app.codility.com/programmers/lessons/2-arrays/odd_occurrences_in_array/
 */
public class L02OddOccurrencesInArray {

    public int solution(int[] A) {
        Map<Integer, Boolean> oddNumbers = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            if (oddNumbers.get(A[i]) == null) {
                oddNumbers.put(A[i], true);
            } else {
                oddNumbers.remove(A[i]);
            }
        }
        return oddNumbers.keySet().iterator().next(); // return first and only one element
    }

    public int solution2(int[] A) {
        int oddValue = 0;
        for (int i = 0; i < A.length; i++) {
            oddValue ^= A[i];
        }
        return oddValue;
    }

}
