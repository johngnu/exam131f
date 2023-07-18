package backtraking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* INF-143
 * Helen Carolina Castillo Mamani
 * Par: "B" 1/2023
 */
public class Hamilton {

    public static void hamiltonianPaths(Graph graph, int v, boolean[] visited,
            List<Integer> path, int n) {
        // si se visitan todos los vértices, entonces existe el camino hamiltoniano
        if (path.size() == n) {
            // imprime el camino hamiltoniano
            System.out.println(path);
            return;
        }

        // Comprueba si todos los bordes que comienzan desde el vértice `v` conducen
        // a una solución o no
        for (int w : graph.adjList.get(v)) {
            // procesa solo los vértices no visitados como el hamiltoniano
            // la ruta visita cada vértice exactamente una vez
            if (!visited[w]) {
                visited[w] = true;
                path.add(w);

                // verifica si agregar el vértice `w` a la ruta conduce
                // a la solución o no
                hamiltonianPaths(graph, w, visited, path, n);

                // retractarse
                visited[w] = false;
                path.remove(path.size() - 1);
            }
        }
    }

    public static void findHamiltonianPaths(Graph graph, int n) {
        // comienza con cada nodo
        for (int start = 0; start < n; start++) {
            // agrega el nodo inicial a la ruta
            List<Integer> path = new ArrayList<>();
            path.add(start);

            // marca el nodo de inicio como visitado
            boolean[] visited = new boolean[n];
            visited[start] = true;

            hamiltonianPaths(graph, start, visited, path, n);
        }
    }

    public static void main(String[] args) {
        // considera un grafo completo que tiene 4 vértices
        List<Edge> edges = Arrays.asList(
                new Edge(0, 1), new Edge(0, 2), new Edge(0, 3),
                new Edge(1, 2), new Edge(1, 3), new Edge(2, 3)
        );

        // número total de nodos en el graph (etiquetados de 0 a 3)
        int n = 4;

        // construye un graph a partir de los bordes dados
        Graph graph = new Graph(edges, n);

        findHamiltonianPaths(graph, n);
    }
}
