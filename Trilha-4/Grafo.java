import java.util.*;

// Classe para representar um grafo simples usando lista de adjacência
class Graph {
    private Map<Integer, List<Integer>> adjList;

    public Graph() {
        adjList = new HashMap<>();
    }

    // Adiciona um vértice ao grafo
    public void addVertex(int vertex) {
        adjList.putIfAbsent(vertex, new ArrayList<>());
    }

    // Adiciona uma aresta ao grafo (não direcionada)
    public void addEdge(int source, int destination) {
        adjList.putIfAbsent(source, new ArrayList<>());
        adjList.putIfAbsent(destination, new ArrayList<>());
        adjList.get(source).add(destination);
        adjList.get(destination).add(source); // Adicionando a aresta reversa para grafos não direcionados
    }

    // Busca em profundidade (DFS)
    public void dfs(int start) {
        Set<Integer> visited = new HashSet<>();
        dfsUtil(start, visited);
    }

    private void dfsUtil(int vertex, Set<Integer> visited) {
        visited.add(vertex);
        System.out.print(vertex + " ");

        for (int neighbor : adjList.get(vertex)) {
            if (!visited.contains(neighbor)) {
                dfsUtil(neighbor, visited);
            }
        }
    }

    // Busca em largura (BFS)
    public void bfs(int start) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        visited.add(start);
        queue.offer(start);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            System.out.print(vertex + " ");

            for (int neighbor : adjList.get(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
    }

    // Exibe o grafo (lista de adjacência)
    public void displayGraph() {
        for (Map.Entry<Integer, List<Integer>> entry : adjList.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            for (int neighbor : entry.getValue()) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }
}

// Grafo Direcionado com o algoritmo de Dijkstra
class DirectedGraph {
    private Map<Integer, List<Edge>> adjList;

    // Classe interna para representar uma aresta com peso
    static class Edge {
        int destination, weight;

        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    public DirectedGraph() {
        adjList = new HashMap<>();
    }

    // Adiciona um vértice ao grafo
    public void addVertex(int vertex) {
        adjList.putIfAbsent(vertex, new ArrayList<>());
    }

    // Adiciona uma aresta direcionada com peso
    public void addEdge(int source, int destination, int weight) {
        adjList.putIfAbsent(source, new ArrayList<>());
        adjList.get(source).add(new Edge(destination, weight));
    }

    // Algoritmo de Dijkstra para encontrar o caminho mais curto
    public void dijkstra(int start) {
        Map<Integer, Integer> distances = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); // Min-heap (distância, nó)

        // Inicializando todas as distâncias como infinito, exceto o nó inicial
        for (int vertex : adjList.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        pq.offer(new int[] {start, 0}); // Coloca o nó de partida na fila de prioridade

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int vertex = current[0];
            int distance = current[1];

            if (visited.contains(vertex)) {
                continue;
            }
            visited.add(vertex);

            // Atualiza a distância para os vizinhos
            for (Edge edge : adjList.getOrDefault(vertex, Collections.emptyList())) {
                int newDist = distance + edge.weight;
                if (newDist < distances.get(edge.destination)) {
                    distances.put(edge.destination, newDist);
                    pq.offer(new int[] {edge.destination, newDist});
                }
            }
        }

        // Exibe as distâncias mínimas de `start` para todos os outros vértices
        for (Map.Entry<Integer, Integer> entry : distances.entrySet()) {
            System.out.println("Distância do nó " + start + " até o nó " + entry.getKey() + ": " + entry.getValue());
        }
    }

    // Exibe o grafo direcionado
    public void displayGraph() {
        for (Map.Entry<Integer, List<Edge>> entry : adjList.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            for (Edge edge : entry.getValue()) {
                System.out.print("(" + edge.destination + ", " + edge.weight + ") ");
            }
            System.out.println();
        }
    }
}

// Classe principal para testar os algoritmos
public class Main {
    public static void main(String[] args) {
        // Grafo simples (não direcionado)
        System.out.println("Grafo simples (não direcionado):");
        Graph graph = new Graph();
        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        
        graph.displayGraph();

        System.out.print("\nDFS (busca em profundidade) a partir do nó 0: ");
        graph.dfs(0);

        System.out.print("\nBFS (busca em largura) a partir do nó 0: ");
        graph.bfs(0);

        // Grafo direcionado
        System.out.println("\n\nGrafo direcionado:");
        DirectedGraph directedGraph = new DirectedGraph();
        directedGraph.addVertex(0);
        directedGraph.addVertex(1);
        directedGraph.addVertex(2);
        directedGraph.addVertex(3);
        directedGraph.addEdge(0, 1, 5);
        directedGraph.addEdge(0, 2, 10);
        directedGraph.addEdge(1, 3, 2);
        directedGraph.addEdge(2, 3, 1);
        
        directedGraph.displayGraph();

        // Algoritmo de Dijkstra a partir do nó 0
        System.out.println("\nAlgoritmo de Dijkstra a partir do nó 0:");
        directedGraph.dijkstra(0);
    }
}
