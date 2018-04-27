package pl.florsoft.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/2-arrays/odd_occurrences_in_array/
 */
public class L02OddOccurrencesInArray2 {

    public int solution(int[] A) {
        int oddValue = 0;
        for (int i = 0; i < A.length; i++) {
            oddValue ^= A[i];
        }
        return oddValue;
    }
}
