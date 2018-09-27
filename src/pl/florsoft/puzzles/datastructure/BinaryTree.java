package pl.florsoft.puzzles.datastructure;

public class BinaryTree<E> {

    public BinaryTreeNode<E> root;

    public BinaryTree() {
    }

    public BinaryTree(BinaryTreeNode<E> root) {
        this.root = root;
    }

    public void inOrderTraversal(BinaryTreeNode<E> treeNode) {
        if (treeNode != null) {
            inOrderTraversal(treeNode.left);
            System.out.println(treeNode.value);
            inOrderTraversal(treeNode.right);
        }
    }

    public void preOrderTraversal(BinaryTreeNode<E> treeNode) {
        if (treeNode != null) {
            System.out.println(treeNode.value);
            preOrderTraversal(treeNode.left);
            preOrderTraversal(treeNode.right);
        }
    }

    public void postOrderTraversal(BinaryTreeNode<E> treeNode) {
        if (treeNode != null) {
            postOrderTraversal(treeNode.left);
            postOrderTraversal(treeNode.right);
            System.out.println(treeNode.value);
        }
    }

    class BinaryTreeNode<E> {

        public E value;
        public BinaryTreeNode<E> left;
        public BinaryTreeNode<E> right;

        public BinaryTreeNode(E value) {
            this.value = value;
        }

    }

}
