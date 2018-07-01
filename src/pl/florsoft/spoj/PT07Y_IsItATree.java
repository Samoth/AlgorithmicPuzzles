package pl.florsoft.spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * task: http://www.spoj.com/problems/PT07Y/
 */
public class PT07Y_IsItATree {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodes = Integer.parseInt(st.nextToken());
        int edges = Integer.parseInt(st.nextToken());
        Graph graph = new Graph(nodes, edges);
        for (int i = 0; i < edges; i++) {
            st = new StringTokenizer(br.readLine());
            graph.addEdge(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        }
        graph.isATree();
    }

    static class Graph {
        private LinkedList<Integer> adjacencyMatrix[];
        private int edges;

        Graph(int vertices, int edges) {
            adjacencyMatrix = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                adjacencyMatrix[i] = new LinkedList<>();
            }
            this.edges = edges;
        }

        void addEdge(int source, int destination) {
            adjacencyMatrix[source].add(destination);
            adjacencyMatrix[destination].add(source);
        }

        void isATree() {
            if (edges != adjacencyMatrix.length - 1) {
                System.out.println("NO");
                return;
            }
            boolean[] visitedMatrix = new boolean[adjacencyMatrix.length];
            boolean result = visitNodes(0, 0, visitedMatrix);
            int countVisited = 0;
            for (boolean nodeVis : visitedMatrix) {
                if (nodeVis) {
                    countVisited++;
                }
            }
            if (result && countVisited == adjacencyMatrix.length) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

        private boolean visitNodes(int node, int prevNode, boolean[] visitedNodes) {
            visitedNodes[node] = true;
            for (Integer nextNode : adjacencyMatrix[node]) {
                if (nextNode != prevNode) {
                    if (visitedNodes[nextNode] || !visitNodes(nextNode, node, visitedNodes)) {
                        return false; // cycle
                    }
                }
            }
            return true;
        }
    }
}
