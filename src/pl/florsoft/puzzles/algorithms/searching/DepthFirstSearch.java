package pl.florsoft.puzzles.algorithms.searching;

import pl.florsoft.puzzles.datastructure.graph.Graph;
import pl.florsoft.puzzles.datastructure.graph.GraphNode;

import java.util.List;
import java.util.ListIterator;

public class DepthFirstSearch<E> {

    public GraphNode find(Graph<E> graph, E value) {
        if (graph == null) {
            return null;
        }
        return find(graph.getNodes(), value);
    }

    private GraphNode find(List<GraphNode<E>> graphNodes, E value) {
        GraphNode node = null;
        ListIterator<GraphNode<E>> iterator = graphNodes.listIterator();
        while (node == null && iterator.hasNext()) {
            GraphNode<E> childNode = iterator.next();
            if (!childNode.visited) {
                childNode.visited = true;
                if (childNode.value.equals(value)) {
                    node = childNode;
                } else if (childNode.hasChildren()) {
                    node = find(childNode.children, value);
                }
            }
        }
        return node;
    }

}
