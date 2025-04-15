// Q2: Topological Sort for Directed Acyclic Graph (DAG)
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TopologicalSort {
    // Class to represent a graph
    static class Graph {
        private int vertices;
        private List<List<Integer>> adjacencyList;
        
        // Constructor
        Graph(int vertices) {
            this.vertices = vertices;
            adjacencyList = new ArrayList<>(vertices);
            
            for (int i = 0; i < vertices; i++) {
                adjacencyList.add(new LinkedList<>());
            }
        }
        
        // Function to add an edge to the graph
        void addEdge(int v, int w) {
            adjacencyList.get(v).add(w);
        }
        
        // Recursive function used by topologicalSort
        void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
            // Mark the current node as visited
            visited[v] = true;
            
            // Recur for all adjacent vertices
            for (Integer neighbor : adjacencyList.get(v)) {
                if (!visited[neighbor]) {
                    topologicalSortUtil(neighbor, visited, stack);
                }
            }
            
            // Push current vertex to stack which stores result
            stack.push(v);
        }
        
        // The function to do Topological Sort
        void topologicalSort() {
            Stack<Integer> stack = new Stack<>();
            
            // Mark all the vertices as not visited
            boolean[] visited = new boolean[vertices];
            
            // Call the recursive helper function to store Topological Sort
            // starting from all vertices one by one
            for (int i = 0; i < vertices; i++) {
                if (!visited[i]) {
                    topologicalSortUtil(i, visited, stack);
                }
            }
            
            // Print contents of stack
            System.out.println("Topological Sort order:");
            while (!stack.empty()) {
                System.out.print(stack.pop() + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        // Create a graph with 6 vertices
        Graph graph = new Graph(6);
        
        // Add edges (Directed Acyclic Graph)
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        
        System.out.println("Graph edges:");
        System.out.println("5 -> 2");
        System.out.println("5 -> 0");
        System.out.println("4 -> 0");
        System.out.println("4 -> 1");
        System.out.println("2 -> 3");
        System.out.println("3 -> 1");
        
        // Perform topological sort
        graph.topologicalSort();
    }
}