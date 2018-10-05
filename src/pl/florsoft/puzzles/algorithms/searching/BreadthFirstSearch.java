package pl.florsoft.puzzles.algorithms.searching;

import pl.florsoft.puzzles.datastructure.Graph;
import pl.florsoft.puzzles.datastructure.GraphNode;
import pl.florsoft.puzzles.datastructure.Queue;

public class BreadthFirstSearch<E> {

    public GraphNode find(Graph<E> graph, E value) {
        if (graph == null || graph.getNodes() == null) {
            return null;
        }
        Queue<GraphNode<E>> nodeQueue = new Queue<>();
        for (GraphNode<E> node : graph.getNodes()) {
            nodeQueue.add(node);
        }
        while (!nodeQueue.isEmpty()) {
            GraphNode<E> node = nodeQueue.remove();
            if (!node.visited) {
                node.visited = true;
                if (node.value.equals(value)) {
                    return node;
                }
                if (node.hasChildren()) {
                    for (GraphNode<E> childNode : node.children) {
                        nodeQueue.add(childNode);
                    }
                }
            }
        }
        return null;
    }

}
