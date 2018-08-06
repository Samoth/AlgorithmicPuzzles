package pl.florsoft.puzzles.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/8-leader/equi_leader/
 */
public class L08EquiLeader {

    public int solution(int[] A) {
        int occures = 0, candidate = A[0];
        for (int i = 0; i < A.length; i++) {
            if (occures == 0) {
                candidate = A[i];
            }
            occures += candidate == A[i] ? 1 : -1;
        }
        occures = 0;
        for (int i = 0; i < A.length; i++) {
            if (candidate == A[i]) {
                occures++;
            }
        }
        int currectOccures = 0, equiLeaders = 0;
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] == candidate) {
                currectOccures++;
            }
            if (currectOccures > (i + 1) / 2 && occures - currectOccures > (A.length - i - 1) / 2) {
                equiLeaders++;
            }
        }
        return equiLeaders;
    }

}
