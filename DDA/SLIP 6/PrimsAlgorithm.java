
// Q1: Prim's Algorithm for Minimum Spanning Tree
import java.util.Arrays;

public class PrimsAlgorithm {
    static class Graph {
        int vertices;
        int[][] adjacencyMatrix;
        
        public Graph(int vertices) {
            this.vertices = vertices;
            adjacencyMatrix = new int[vertices][vertices];
            
            // Initialize the adjacency matrix with 0
            for (int i = 0; i < vertices; i++) {
                Arrays.fill(adjacencyMatrix[i], 0);
            }
        }
        
        // Add edge
        public void addEdge(int source, int destination, int weight) {
            // Undirected graph
            adjacencyMatrix[source][destination] = weight;
            adjacencyMatrix[destination][source] = weight;
        }
        
        // Find the vertex with minimum key value, from the set of vertices not yet included in MST
        int minKey(int[] key, boolean[] mstSet) {
            int min = Integer.MAX_VALUE, min_index = -1;
            
            for (int v = 0; v < vertices; v++) {
                if (!mstSet[v] && key[v] < min) {
                    min = key[v];
                    min_index = v;
                }
            }
            
            return min_index;
        }
        
        // Print the constructed MST
        void printMST(int[] parent) {
            System.out.println("Edge \tWeight");
            for (int i = 1; i < vertices; i++) {
                System.out.println(parent[i] + " - " + i + "\t" + adjacencyMatrix[i][parent[i]]);
            }
        }
        
        // Implement Prim's algorithm
        void primMST() {
            // Array to store constructed MST
            int[] parent = new int[vertices];
            
            // Key values used to pick minimum weight edge
            int[] key = new int[vertices];
            
            // To represent set of vertices included in MST
            boolean[] mstSet = new boolean[vertices];
            
            // Initialize all keys as INFINITE
            Arrays.fill(key, Integer.MAX_VALUE);
            
            // Always include first vertex in MST.
            key[0] = 0; // Make key 0 so that this vertex is picked as first vertex
            parent[0] = -1; // First node is always root of MST
            
            // The MST will have vertices-1 edges
            for (int count = 0; count < vertices - 1; count++) {
                // Pick the minimum key vertex from the set of vertices not yet included in MST
                int u = minKey(key, mstSet);
                
                // Add the picked vertex to the MST Set
                mstSet[u] = true;
                
                // Update key value and parent index of the adjacent vertices of the picked vertex.
                // Consider only those vertices which are not yet included in MST
                for (int v = 0; v < vertices; v++) {
                    // adjacencyMatrix[u][v] is non zero only for adjacent vertices of u
                    // mstSet[v] is false for vertices not yet included in MST
                    // Update the key only if adjacencyMatrix[u][v] is smaller than key[v]
                    if (adjacencyMatrix[u][v] != 0 && !mstSet[v] && adjacencyMatrix[u][v] < key[v]) {
                        parent[v] = u;
                        key[v] = adjacencyMatrix[u][v];
                    }
                }
            }
            
            // Print the constructed MST
            printMST(parent);
        }
    }
    
    public static void main(String[] args) {
        // Create a graph with 5 vertices
        Graph graph = new Graph(5);
        
        // Add edges with weights
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 6);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 4, 9);
        
        // Find MST using Prim's algorithm
        System.out.println("Minimum Spanning Tree using Prim's algorithm:");
        graph.primMST();
    }
}
