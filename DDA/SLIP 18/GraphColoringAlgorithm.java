// Graph Coloring Algorithm 
import java.util.*;

public class GraphColoringAlgorithm {
    private int V; // Number of vertices
    private List<Integer>[] adjList;
    
    public GraphColoringAlgorithm(int v) {
        V = v;
        adjList = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adjList[i] = new ArrayList<>();
        }
    }
    
    public void addEdge(int v, int w) {
        adjList[v].add(w);
        adjList[w].add(v); // undirected graph
    }
    
    public void greedyColoring() {
        // Result array to store the assigned colors
        int[] result = new int[V];
        
        // Initialize all vertices as unassigned (-1)
        Arrays.fill(result, -1);
        
        // Assign the first color (0) to the first vertex
        result[0] = 0;
        
        // Temporary array to store the available colors
        boolean[] available = new boolean[V];
        
        // Initially all colors are available
        Arrays.fill(available, true);
        
        // Assign colors to remaining V-1 vertices
        for (int u = 1; u < V; u++) {
            // Process all adjacent vertices and mark their colors as unavailable
            for (int i : adjList[u]) {
                if (result[i] != -1) {
                    available[result[i]] = false;
                }
            }
            
            // Find the first available color
            int cr;
            for (cr = 0; cr < V; cr++) {
                if (available[cr]) {
                    break;
                }
            }
            
            // Assign the found color
            result[u] = cr;
            
            // Reset the available array for next iteration
            Arrays.fill(available, true);
        }
        
        // Print the result
        System.out.println("Vertex coloring:");
        for (int u = 0; u < V; u++) {
            System.out.println("Vertex " + u + " --> Color " + result[u]);
        }
    }
    
    public static void main(String[] args) {
        GraphColoringAlgorithm g = new GraphColoringAlgorithm(5);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        
        System.out.println("Coloring of graph:");
        g.greedyColoring();
    }
}