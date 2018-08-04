package pl.florsoft.puzzles.codility;

import java.util.Stack;

/**
 * Task: https://app.codility.com/programmers/lessons/7-stacks_and_queues/fish/
 */
public class L07Fish {

    public int solution(int[] A, int[] B) {
        int fishAlive = A.length;
        Stack<Integer> fishDownSize = new Stack<>();
        for (int i = 0; i < B.length; i++) {
            if (B[i] == 1) {
                fishDownSize.push(A[i]);
            } else {
                while (!fishDownSize.isEmpty() && A[i] > fishDownSize.peek()) {
                    fishAlive--;
                    fishDownSize.pop();
                }
                if (!fishDownSize.isEmpty() && A[i] < fishDownSize.peek()) {
                    fishAlive--;
                }
            }
        }
        return fishAlive;
    }

}
