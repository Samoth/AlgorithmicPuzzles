package pl.florsoft.puzzles.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/16-greedy_algorithms/max_nonoverlapping_segments/
 */
public class L16MaxNonoverlappingSegments {

    public int solution(int[] A, int[] B) {
        int elems = A.length;
        if (elems == 0) {
            return 0;
        }
        int lastElem = -1;
        int appMaxElem = 0;
        for (int i = 0; i < elems; i++) {
            if (A[i] > lastElem) {
                appMaxElem++;
                lastElem = B[i];
            }
        }
        return appMaxElem;
    }

}
