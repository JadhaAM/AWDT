
// Q1: Topological Sorting with Time Complexity
import java.util.*;

public class TopologicalSort {
    private int V;
    private List<Integer>[] adjList;
    
    public TopologicalSort(int v) {
        this.V = v;
        adjList = new List[v];
        for (int i = 0; i < v; i++) {
            adjList[i] = new ArrayList<>();
        }
    }
    
    public void addEdge(int v, int w) {
        adjList[v].add(w);
    }
    public void topologicalSort() {
        long startTime = System.nanoTime();
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }
        System.out.println("Topological Sort Order:");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Time taken: " + duration + " nanoseconds");
        System.out.println("Time Complexity: O(V + E) where V is vertices and E is edges");
    }
    private void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        for (Integer neighbor : adjList[v]) {
            if (!visited[neighbor]) {
                topologicalSortUtil(neighbor, visited, stack);
            }
        }
        stack.push(v);
    }  
    
    public static void main(String[] args) {
        TopologicalSort g = new TopologicalSort(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        
        g.topologicalSort();
    }
}