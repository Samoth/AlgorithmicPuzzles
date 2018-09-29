package pl.florsoft.puzzles.datastructure;

import java.util.List;

public class Graph<E> {

    public List<GraphNode> nodes;

    static class GraphNode<E> {

        public E value;
        public List<GraphNode> children;

        public GraphNode(E value) {
            this.value = value;
        }

    }

}
