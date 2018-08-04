package pl.florsoft.puzzles.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/2-arrays/cyclic_rotation/
 */
public class L02CyclicRotation {

    public int[] solution(int[] A, int K) {
        if (A.length == 0 || K % A.length == 0) {
            return A;
        }
        int shiftValue = K % A.length;
        int[] buffer = new int[shiftValue];
        for (int i = A.length - 1; i >= 0; i--) {
            if (i + shiftValue > A.length - 1) {
                buffer[(i + shiftValue) % A.length] = A[i];
            } else {
                A[i + shiftValue] = A[i];
            }
        }
        for (int i = 0; i < buffer.length; i++) {
            A[i] = buffer[i];
        }
        return A;
    }
}
