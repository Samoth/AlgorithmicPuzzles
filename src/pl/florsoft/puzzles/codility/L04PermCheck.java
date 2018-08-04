package pl.florsoft.puzzles.codility;

/**
 * https://app.codility.com/programmers/lessons/4-counting_elements/perm_check/
 */
public class L04PermCheck {

    public int solution(int[] A) {
        boolean[] occures = new boolean[A.length + 1];
        for (int val: A) {
            if (val < 1 || val > A.length) {
                return 0;
            }
            occures[val] = !occures[val];
            if (!occures[val]) {
                return 0;
            }
        }
        return 1;
    }

}
