package pl.florsoft.puzzles.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/11-sieve_of_eratosthenes/count_semiprimes/
 */
public class L11CountSemiprimes {

    public int[] solution(int N, int[] P, int[] Q) {
        int[] result = new int[P.length];
        int[] divisors = new int[N + 1];
        int[] cache = new int[N + 1];
        int j = 2;
        int k;
        while (j * j <= N) {
            if (divisors[j] == 0) {
                k = j * j;
                while (k <= N) {
                    if (divisors[k] == 0) {
                        divisors[k] = j;
                    }
                    k += j;
                }
            }
            j++;
        }
        for (int i = 1; i < cache.length; i++) {
            if (divisors[i] > 0 && divisors[i / divisors[i]] == 0) {
                cache[i] = cache[i - 1] + 1;
            } else {
                cache[i] = cache[i - 1];
            }
        }
        for (int i = 0; i < P.length; i++) {
            result[i] = cache[Q[i]] - cache[P[i] - 1];
        }
        return result;
    }

}
