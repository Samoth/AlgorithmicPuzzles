package pl.florsoft.puzzles.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/11-sieve_of_eratosthenes/count_non_divisible/
 */
public class L11CountNonDivisible {

    public int[] solution(int[] A) {
        int[] occurences = new int[2 * A.length + 1];
        int[] result = new int[A.length];
        for (int val : A) {
            occurences[val] = occurences[val] + 1;
        }
        for (int i = 0; i < A.length; i++) {
            int val = A[i];
            int j = 1;
            int currentCnt = 0;
            while (j * j <= val && j * j > 0) {
                if (val % j == 0) {
                    currentCnt += occurences[j];
                    if (j != val / j) {
                        currentCnt += occurences[val / j];
                    }
                }
                j++;
            }
            result[i] = A.length - currentCnt;
        }
        return result;
    }

}
