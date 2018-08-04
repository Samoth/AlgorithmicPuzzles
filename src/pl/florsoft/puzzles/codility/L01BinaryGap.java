package pl.florsoft.puzzles.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/1-iterations/binary_gap/
 */
public class L01BinaryGap {

    public int solution(int N) {
        int actualGap = 0, maxGap = 0;
        boolean isFindOne = false;
        while (N != 0) {
            if ((N & 1) == 1) {
                isFindOne = true;
                actualGap = 0;
            } else {
                if (isFindOne) {
                    actualGap++;
                    if (actualGap > maxGap) {
                        maxGap = actualGap;
                    }
                }
            }
            N = N >> 1;
        }
        return maxGap;
    }
}
