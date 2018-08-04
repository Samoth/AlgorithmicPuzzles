package pl.florsoft.puzzles.codility;

public class L05CountDiv {

    public int solution(int A, int B, int K) {
        int count = A % K == 0 || B % K == 0 ? 1 : 0;
        double greaterNumber = B < K ? 0 : (double) B / K;
        double lessNumber = A < K ? 0 : (double) A / K;
        return count + (int) (greaterNumber - lessNumber);
    }
}
