// Hamiltonian Cycle 

import java.util.*;

public class HamiltonianCycle {
    private int V;
    private List<Integer>[] adjList;
    private int[] path;
    
    public HamiltonianCycle(int v) {
        this.V = v;
        path = new int[V];
        
        adjList = new List[V];
        for (int i = 0; i < V; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        Arrays.fill(path, -1);
        path[0] = 0; // Start from vertex 0
    }
    
    public void addEdge(int v, int w) {
        adjList[v].add(w);
        adjList[w].add(v);
    }
    
    public boolean isHamiltonianCycle() {
        if (!findHamiltonianPath(1)) {
            System.out.println("Hamiltonian cycle does not exist");
            return false;
        }
        
        // Check if there is an edge from the last vertex to the first vertex
        int lastVertex = path[V - 1];
        if (!adjList[lastVertex].contains(path[0])) {
            System.out.println("Hamiltonian path exists but not a cycle");
            return false;
        }
        
        System.out.println("Hamiltonian cycle exists");
        printSolution();
        return true;
    }
    
    private boolean findHamiltonianPath(int pos) {
        // Base case: if all vertices are included in the path
        if (pos == V) {
            return true;
        }
        
        // Try different vertices for the position
        for (int v = 1; v < V; v++) {
            // Check if this vertex can be added to the path
            if (isSafe(v, pos)) {
                path[pos] = v;
                
                // Recur to check for the remaining path
                if (findHamiltonianPath(pos + 1)) {
                    return true;
                }
                
                // If adding vertex v doesn't lead to a solution, remove it
                path[pos] = -1;
            }
        }
        
        return false;
    }
    
    private boolean isSafe(int v, int pos) {
        // Check if this vertex is adjacent to the previously added vertex
        if (!adjList[path[pos - 1]].contains(v)) {
            return false;
        }
        
        // Check if the vertex has already been included in the path
        for (int i = 0; i < pos; i++) {
            if (path[i] == v) {
                return false;
            }
        }
        
        return true;
    }
    
    private void printSolution() {
        System.out.println("Hamiltonian Cycle:");
        for (int i = 0; i < V; i++) {
            System.out.print(path[i] + " ");
        }
        System.out.println(path[0]); // Return to starting vertex
    }
    
    public static void main(String[] args) {
        // Create a graph with Hamiltonian cycle
        HamiltonianCycle hc = new HamiltonianCycle(5);
        hc.addEdge(0, 1);
        hc.addEdge(0, 3);
        hc.addEdge(1, 2);
        hc.addEdge(1, 3);
        hc.addEdge(1, 4);
        hc.addEdge(2, 4);
        hc.addEdge(3, 4);
        
        hc.isHamiltonianCycle();
    }
}