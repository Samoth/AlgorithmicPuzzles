package pl.florsoft.puzzles.codility;

import java.util.Stack;

/**
 * Task: https://app.codility.com/programmers/lessons/7-stacks_and_queues/stone_wall/
 */
public class L07StoneWall {

    public int solution(int[] H) {
        if (H == null || H.length == 0) {
            return 0;
        }
        int blocksToMerge = 0;
        Stack<Integer> heightStack = new Stack<>();
        for (int i = 0; i < H.length; i++) {
            int currentElement = H[i];
            while (!heightStack.isEmpty() && currentElement < heightStack.peek()) {
                heightStack.pop();
            }
            if (heightStack.isEmpty()) {
                heightStack.push(currentElement);
                continue;
            }
            Integer lastElementOnStack = heightStack.peek();
            if (lastElementOnStack == currentElement) {
                blocksToMerge++;
            } else if (currentElement > lastElementOnStack) {
                heightStack.push(currentElement);
            }
        }
        return H.length - blocksToMerge;
    }

    public int solution2(int[] H) {
        if (H == null || H.length == 0) {
            return 0;
        }
        int result = 0;
        Stack<Integer> heightStack = new Stack<>();
        for (int i = 0; i < H.length; i++) {
            int currentElement = H[i];
            while (!heightStack.isEmpty() && currentElement < heightStack.peek()) {
                heightStack.pop();
            }
            if (heightStack.isEmpty() || heightStack.peek() < currentElement) {
                heightStack.push(currentElement);
                result++;
            }
        }
        return result;
    }

}
