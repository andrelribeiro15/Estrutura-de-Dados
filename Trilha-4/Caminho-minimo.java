import java.util.*;

class Graph {
    private Map<Integer, List<Edge>> adjList;

    // Classe para representar uma aresta com peso
    static class Edge {
        int destination, weight;

        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    public Graph() {
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

    // Algoritmo de Dijkstra para encontrar o caminho mais curto a partir de um vértice
    public void dijkstra(int start) {
        Map<Integer, Integer> distances = new HashMap<>();
        Map<Integer, Integer> previous = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); // Min-heap (distância, nó)

        // Inicializa todas as distâncias como infinito
        for (int vertex : adjList.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        pq.offer(new int[] {start, 0}); // Adiciona o nó de origem à fila de prioridade

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int vertex = current[0];
            int distance = current[1];

            if (visited.contains(vertex)) {
                continue;
            }
            visited.add(vertex);

            // Atualiza a distância dos vizinhos
            for (Edge edge : adjList.getOrDefault(vertex, Collections.emptyList())) {
                int newDist = distance + edge.weight;
                if (newDist < distances.get(edge.destination)) {
                    distances.put(edge.destination, newDist);
                    previous.put(edge.destination, vertex);
                    pq.offer(new int[] {edge.destination, newDist});
                }
            }
        }

        // Exibe as distâncias mais curtas e os caminhos
        for (Map.Entry<Integer, Integer> entry : distances.entrySet()) {
            System.out.println("Distância do nó " + start + " até o nó " + entry.getKey() + ": " + entry.getValue());
            printPath(previous, entry.getKey());
        }
    }

    // Função para reconstruir o caminho a partir do nó de origem
    private void printPath(Map<Integer, Integer> previous, int target) {
        if (!previous.containsKey(target)) {
            return;
        }
        LinkedList<Integer> path = new LinkedList<>();
        for (Integer at = target; at != null; at = previous.get(at)) {
            path.addFirst(at);
        }
        System.out.println("Caminho: " + path);
    }

    // Algoritmo de Floyd-Warshall para encontrar o caminho mais curto entre todos os pares de vértices
    public void floydWarshall() {
        int n = adjList.size();
        int[][] dist = new int[n][n];
        int[][] next = new int[n][n];

        // Inicializa as distâncias com infinito e os caminhos
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) {
            dist[i][i] = 0;
        }

        // Preenche a matriz de distâncias e a matriz de próximos
        Map<Integer, Integer> vertexMap = new HashMap<>();
        int index = 0;
        for (int vertex : adjList.keySet()) {
            vertexMap.put(vertex, index++);
        }

        for (int u : adjList.keySet()) {
            for (Edge edge : adjList.get(u)) {
                int i = vertexMap.get(u);
                int j = vertexMap.get(edge.destination);
                dist[i][j] = edge.weight;
                next[i][j] = j;
            }
        }

        // Aplica o algoritmo de Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE && dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        // Exibe as distâncias mínimas entre todos os pares de vértices
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][j] == Integer.MAX_VALUE) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }

        // Exibe o caminho mais curto entre todos os pares de vértices
        System.out.println("\nCaminhos mais curtos entre os pares de vértices:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][j] != Integer.MAX_VALUE) {
                    System.out.print("Caminho de " + i + " a " + j + ": ");
                    printPathFloyd(next, i, j);
                    System.out.println();
                }
            }
        }
    }

    // Função auxiliar para imprimir o caminho utilizando a matriz `next`
    private void printPathFloyd(int[][] next, int i, int j) {
        if (next[i][j] == -1) {
            System.out.print("Não há caminho.");
            return;
        }
        LinkedList<Integer> path = new LinkedList<>();
        for (int at = i; at != j; at = next[at][j]) {
            path.add(at);
        }
        path.add(j);
        System.out.println(path);
    }
}

// Classe principal para testar os algoritmos de Dijkstra e Floyd-Warshall
public class Main {
    public static void main(String[] args) {
        // Criando o grafo ponderado
        Graph graph = new Graph();
        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);

        // Adicionando arestas com peso
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 5);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 1, 3);
        graph.addEdge(2, 3, 9);
        graph.addEdge(2, 4, 2);
        graph.addEdge(3, 4, 4);

        // Testando o algoritmo de Dijkstra
        System.out.println("Algoritmo de Dijkstra a partir do nó 0:");
        graph.dijkstra(0);

        // Testando o algoritmo de Floyd-Warshall
        System.out.println("\nAlgoritmo de Floyd-Warshall:");
        graph.floydWarshall();
    }
}
