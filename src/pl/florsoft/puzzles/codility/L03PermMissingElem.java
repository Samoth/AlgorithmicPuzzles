package pl.florsoft.puzzles.codility;

/**
 * https://app.codility.com/programmers/lessons/3-time_complexity/perm_missing_elem/
 */
public class L03PermMissingElem {

    public int solution(int[] A) {
        long sum = (long) (A.length + 1) * (A.length + 2) / 2;
        for (int i = 0; i < A.length; i++) {
            sum -= A[i];
        }
        return (int) sum;
    }

}
