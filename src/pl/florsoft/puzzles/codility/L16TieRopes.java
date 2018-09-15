package pl.florsoft.puzzles.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/16-greedy_algorithms/tie_ropes/
 */
public class L16TieRopes {

    public int solution(int K, int[] A) {
        int appMaxNumberOfRobes = 0;
        int currentRobeLength = 0;
        for (int i = 0; i < A.length; i++) {
            currentRobeLength += A[i];
            if (A[i] >= K || currentRobeLength >= K) {
                appMaxNumberOfRobes++;
                currentRobeLength = 0;
            }
        }
        return appMaxNumberOfRobes;
    }

}
