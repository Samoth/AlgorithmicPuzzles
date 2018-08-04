package pl.florsoft.puzzles.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/15-caterpillar_method/abs_distinct/
 */
public class L15AbsDistinct {

    public int solution(int[] A) {
        int back = 0, front = A.length - 1, duplicates = 0;
        while (back < front) {
            while (back + 1 < front && Math.abs((long)A[back]) < Math.abs((long)A[front])) {
                front--;
                if (A[front] == A[front + 1]) {
                    duplicates++;
                }
            }
            back++;
            if (Math.abs((long)A[back - 1]) == Math.abs((long)A[front]) || A[back - 1] == A[back]) {
                duplicates++;
            }
        }
        return A.length - duplicates;
    }
}