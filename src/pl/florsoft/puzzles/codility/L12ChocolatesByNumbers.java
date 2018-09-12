package pl.florsoft.puzzles.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/12-euclidean_algorithm/chocolates_by_numbers/
 */
public class L12ChocolatesByNumbers {

    public int solution(int N, int M) {
        int gcd = computeGcd(N, M);
        long lcm = ((long) N * M) / gcd;
        return (int) (lcm / M);
    }

    private int computeGcd(int a, int b) {
        if (a % b == 0) {
            return b;
        } else {
            return computeGcd(b, a % b);
        }
    }

}
