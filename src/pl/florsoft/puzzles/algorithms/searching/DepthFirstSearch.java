package pl.florsoft.puzzles.algorithms.searching;

import pl.florsoft.puzzles.datastructure.graph.Graph;
import pl.florsoft.puzzles.datastructure.graph.GraphNode;

import java.util.List;

public class DepthFirstSearch<E> {

    public GraphNode find(Graph<E> graph, E value) {
        if (graph == null) {
            return null;
        }
        return find(graph.getNodes(), value);
    }

    private GraphNode find(List<GraphNode<E>> graphNodes, E value) {
        for (GraphNode<E> childNode : graphNodes) {
            if (!childNode.visited) {
                childNode.visited = true;
                if (childNode.value.equals(value)) {
                    return childNode;
                } else if (childNode.hasChildren()) {
                    return find(childNode.children, value);
                }
            }
        }
        return null;
    }

}
