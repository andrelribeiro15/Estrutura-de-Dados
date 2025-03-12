import java.util.*;

class DisjointSet {
    private int[] parent;
    private int[] rank;

    public DisjointSet(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}

class Graph {
    private int vertices;
    private List<int[]> edges;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.edges = new ArrayList<>();
    }

    public void addEdge(int u, int v) {
        edges.add(new int[]{u, v});
    }

    public void findConnectedComponents() {
        DisjointSet ds = new DisjointSet(vertices);
        for (int[] edge : edges) {
            ds.union(edge[0], edge[1]);
        }

        Map<Integer, List<Integer>> components = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            int root = ds.find(i);
            components.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
        }

        System.out.println("Componentes Conectados:");
        for (List<Integer> component : components.values()) {
            System.out.println(component);
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(10);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);
        graph.addEdge(8, 9);
        
        graph.findConnectedComponents();
    }
}
