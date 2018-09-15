package pl.florsoft.puzzles.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/99-future_training/tree_height/
 */
public class L99TreeHeight {

    public int solution(Tree T) {
        return computeHeight(T, -1);
    }

    private int computeHeight(Tree tree, int height) {
        if (tree == null) {
            return height;
        } else if (tree.l == null && tree.r == null) {
            return height + 1;
        }
        return Math.max(computeHeight(tree.l, height + 1), computeHeight(tree.r, height + 1));
    }

    class Tree {
        public int x;
        public Tree l;
        public Tree r;
    }

}
