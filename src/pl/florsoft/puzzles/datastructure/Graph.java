package pl.florsoft.puzzles.datastructure;

import java.util.ArrayList;
import java.util.List;

public class Graph<E> {

    private List<GraphNode<E>> nodes;

    public Graph() {
    }

    public Graph(List<GraphNode<E>> nodes) {
        this.nodes = nodes;
    }

    public void addNode(GraphNode<E> node) {
        if (nodes == null) {
            nodes = new ArrayList<>();
        }
        nodes.add(node);
    }

    public List<GraphNode<E>> getNodes() {
        return nodes;
    }

}
