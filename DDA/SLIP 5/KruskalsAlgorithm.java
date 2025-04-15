
// Q1: Kruskal's Algorithm for Minimum Spanning Tree
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class KruskalsAlgorithm {
    // Class to represent an edge in graph
    static class Edge {
        int src, dest, weight;
        
        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }
    
    // Class to represent a graph
    static class Graph {
        int vertices;
        ArrayList<Edge> allEdges = new ArrayList<>();
        
        public Graph(int vertices) {
            this.vertices = vertices;
        }
        
        // Add edge to graph
        public void addEdge(int src, int dest, int weight) {
            Edge edge = new Edge(src, dest, weight);
            allEdges.add(edge);
        }
        
        // Find the subset of an element i
        int find(int[] parent, int i) {
            if (parent[i] != i) {
                parent[i] = find(parent, parent[i]); // Path compression
            }
            return parent[i];
        }
        
        // Union of two subsets
        void union(int[] parent, int[] rank, int x, int y) {
            int xroot = find(parent, x);
            int yroot = find(parent, y); 
            
            if (rank[xroot] < rank[yroot]) {
                parent[xroot] = yroot;
            } else if (rank[xroot] > rank[yroot]) {
                parent[yroot] = xroot;
            } else {
                // If ranks are same, make one as root and increment its rank
                parent[yroot] = xroot;
                rank[xroot]++;
            }
        }
        
        // Implement Kruskal's algorithm
        void kruskalMST() {
            ArrayList<Edge> result = new ArrayList<>();
            
            // Sort all edges in non-decreasing order of their weight
            Collections.sort(allEdges, new Comparator<Edge>() {
                @Override
                public int compare(Edge a, Edge b) {
                    return a.weight - b.weight;
                }
            });
            
            // Create V subsets with single elements
            int[] parent = new int[vertices];
            int[] rank = new int[vertices];
            
            // Initialize parent and rank arrays
            for (int i = 0; i < vertices; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
            
            int e = 0; // Index for result array
            int i = 0; // Index for sorted edges
            
            // Minimum Spanning Tree will have V-1 edges
            while (e < vertices - 1 && i < allEdges.size()) {
                // Pick the smallest edge
                Edge nextEdge = allEdges.get(i++);
                
                int x = find(parent, nextEdge.src);
                int y = find(parent, nextEdge.dest);
                
                // If including this edge doesn't cause cycle, include it
                if (x != y) {
                    result.add(nextEdge);
                    e++;
                    union(parent, rank, x, y);
                }
            }
            
            // Print the constructed MST
            System.out.println("Edges in the Minimum Spanning Tree:");
            int totalWeight = 0;
            for (Edge edge : result) {
                System.out.println(edge.src + " -- " + edge.dest + " == " + edge.weight);
                totalWeight += edge.weight;
            }
            System.out.println("Total weight of MST: " + totalWeight);
        }
    }
    
    public static void main(String[] args) {
        // Create a graph with 4 vertices
        Graph graph = new Graph(4);
        
        // Add edges
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);
        
        // Run Kruskal's algorithm
        graph.kruskalMST();
    }
}

