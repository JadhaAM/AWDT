
// Q1: Dijkstra's Algorithm
import java.util.Arrays;

public class DijkstrasAlgorithm {
    // Class to represent a weighted graph
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
        
        // Add edge to the graph
        public void addEdge(int source, int destination, int weight) {
            adjacencyMatrix[source][destination] = weight;
            adjacencyMatrix[destination][source] = weight; // For undirected graph
        }
        
        // Find the vertex with minimum distance
        int minDistance(int[] distance, boolean[] shortestPathSet) {
            int min = Integer.MAX_VALUE, min_index = -1;
            
            for (int v = 0; v < vertices; v++) {
                if (!shortestPathSet[v] && distance[v] <= min) {
                    min = distance[v];
                    min_index = v;
                }
            }
            
            return min_index;
        }
        
        // Print the constructed distance array
        void printSolution(int[] distance) {
            System.out.println("Vertex \t Distance from Source");
            for (int i = 0; i < vertices; i++) {
                System.out.println(i + " \t\t " + distance[i]);
            }
        }
        
        // Implement Dijkstra's algorithm
        void dijkstra(int src) {
            // Distance array to store shortest distance from src to i
            int[] distance = new int[vertices];
            
            // shortestPathSet[i] will be true if vertex i is included in shortest path tree
            boolean[] shortestPathSet = new boolean[vertices];
            
            // Initialize all distances as INFINITE and shortestPathSet[] as false
            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[src] = 0; // Distance of source vertex from itself is always 0
            
            // Find shortest path for all vertices
            for (int count = 0; count < vertices - 1; count++) {
                // Pick the minimum distance vertex from the set of vertices not yet processed
                int u = minDistance(distance, shortestPathSet);
                
                // Mark the picked vertex as processed
                shortestPathSet[u] = true;
                
                // Update distance value of the adjacent vertices of the picked vertex
                for (int v = 0; v < vertices; v++) {
                    // Update distance[v] only if:
                    // 1. There is an edge from u to v
                    // 2. Vertex v is not in shortestPathSet
                    // 3. Distance through u is smaller than current value of distance[v]
                    if (!shortestPathSet[v] && adjacencyMatrix[u][v] != 0 &&
                        distance[u] != Integer.MAX_VALUE &&
                        distance[u] + adjacencyMatrix[u][v] < distance[v]) {
                        distance[v] = distance[u] + adjacencyMatrix[u][v];
                    }
                }
            }
            
            // Print the constructed distance array
            printSolution(distance);
        }
    }
    
    public static void main(String[] args) {
        // Create a graph with 6 vertices
        Graph graph = new Graph(6);
        
        // Add edges with weights
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(2, 4, 5);
        graph.addEdge(3, 4, 1);
        graph.addEdge(3, 5, 7);
        graph.addEdge(4, 5, 2);
        
        // Find shortest paths from vertex 0
        System.out.println("Shortest paths from vertex 0 using Dijkstra's algorithm:");
        graph.dijkstra(0);
    }
}

