package pl.florsoft.puzzles.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/flags/
 */
public class L10Flags {

    public int solution(int[] A) {
        if (A.length < 3) {
            return 0;
        }
        int[] nextPeak = new int[A.length];
        int lastPeak = -1, peakCnt = 0;
        for (int i = A.length - 1; i > 0; i--) {
            if (i < A.length - 1 && A[i] > A[i - 1] && A[i] > A[i + 1]) {
                nextPeak[i] = i;
                lastPeak = i;
                peakCnt++;
            } else {
                nextPeak[i] = lastPeak;
            }
        }
        int flagNumber = 1;
        while (flagNumber * flagNumber - flagNumber <= A.length && flagNumber * flagNumber > 0 && flagNumber <= peakCnt) {
            int nextPeakIdx = nextPeak[1];
            int numberOfFlagSet = 0;
            while (nextPeakIdx > 0 && nextPeakIdx < A.length - 1) {
                numberOfFlagSet++;
                if (nextPeakIdx + flagNumber < A.length) {
                    nextPeakIdx = nextPeak[nextPeakIdx + flagNumber];
                } else {
                    break;
                }
            }
            if (numberOfFlagSet < flagNumber) {
                break;
            }
            flagNumber++;
        }
        return flagNumber - 1;
    }

}
