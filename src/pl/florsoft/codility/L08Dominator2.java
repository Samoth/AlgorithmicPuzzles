package pl.florsoft.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/8-leader/dominator/
 */
public class L08Dominator2 {

    public int solution(int[] A) {
        Integer candidate = null;
        int occurs = 0;
        for (int i = 0; i < A.length; i++) {
            if (occurs == 0) {
                candidate = A[i];
            }
            occurs += candidate == A[i] ? 1 : -1;
            if (occurs > A.length / 2) {
                return i;
            }
        }
        if (occurs == 0) {
            return -1;
        }
        occurs = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == candidate) {
                occurs++;
                if (occurs > A.length / 2) {
                    return i;
                }
            }
        }
        return -1;
    }
}
