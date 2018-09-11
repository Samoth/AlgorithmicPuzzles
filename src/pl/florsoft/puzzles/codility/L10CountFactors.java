package pl.florsoft.puzzles.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/count_factors/
 */
public class L10CountFactors {

    public int solution(int N) {
        if (N <= 2) {
            return N;
        }
        int i = 2;
        int count = 2;
        while (i * i < N && i * i > 0) {
            if (N % i == 0) {
                count += 2;
            }
            i++;
        }
        if (i * i == N) {
            count++;
        }
        return count;
    }

}
