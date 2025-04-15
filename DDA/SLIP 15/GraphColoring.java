// Graph Coloring Algorithm 

import java.util.*;

public class GraphColoring {
    private int V; // Number of vertices
    private LinkedList<Integer>[] adjList;
    
    public GraphColoring(int v) {
        V = v;
        adjList = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adjList[i] = new LinkedList<>();
        }
    }
    
    public void addEdge(int v, int w) {
        adjList[v].add(w);
        adjList[w].add(v); // undirected graph
    }
    
    public void greedyColoring() {
        int[] result = new int[V];
        
        // Initialize all vertices as unassigned
        Arrays.fill(result, -1);
        
        // Assign the first color to first vertex
        result[0] = 0;
        
        // A temporary array to store the available colors
        boolean[] available = new boolean[V];
        
        // By default, all colors are available
        Arrays.fill(available, true);
        
        // Assign colors to remaining V-1 vertices
        for (int u = 1; u < V; u++) {
            // Process all adjacent vertices and flag their colors as unavailable
            for (Integer i : adjList[u]) {
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
            
            // Reset the available[] array for the next iteration
            Arrays.fill(available, true);
        }
        
        // Print the result
        for (int u = 0; u < V; u++) {
            System.out.println("Vertex " + u + " ---> Color " + result[u]);
        }
    }
    
    public static void main(String[] args) {
        GraphColoring g = new GraphColoring(5);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        
        System.out.println("Coloring of the graph:");
        g.greedyColoring();
    }
}