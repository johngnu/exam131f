package backtraking;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    // Una lista de listas para representar una lista de adyacencia
    List<List<Integer>> adjList = null;

    Graph(List<Edge> edges, int n) {
        adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        // Agrega bordes al grafo no dirigido
        for (Edge edge : edges) {
            int src = edge.source;
            int dest = edge.dest;

            adjList.get(src).add(dest);
            adjList.get(dest).add(src);
        }
    }
}
