package pl.florsoft.puzzles.datastructure.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphNode<E> {

    public E value;
    public boolean visited;
    public List<GraphNode<E>> children;

    public GraphNode(E value) {
        this.value = value;
    }

    public void addNode(GraphNode<E> node) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(node);
    }

    public boolean hasChildren() {
        return children != null && !children.isEmpty();
    }

    @Override
    public String toString() {
        return "GraphNode{" +
                "value=" + value +
                ", visited=" + visited +
                ", children=" + (children != null ? children.size() : 0) +
                '}';
    }

}
