package pl.florsoft.puzzles.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/99-future_training/str_symmetry_point/
 */
public class L99StrSymmetryPoint {

    public int solution(String S) {
        int length = S.length();
        if (length % 2 == 0) {
            return -1;
        }
        for (int i = 0; i < length / 2; i++) {
            if (S.charAt(i) != S.charAt(length - 1 - i)) {
                return -1;
            }
        }
        return length / 2;
    }

}
