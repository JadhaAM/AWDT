// BFS (Breadth First Search) Implementation 

import java.util.*;

public class BFSImplementation {
    private int V; // Number of vertices
    private LinkedList<Integer>[] adjList;

    public BFSImplementation(int v) {
        V = v;
        adjList = new LinkedList[v];
        for (int i = 0; i < v; i++)
            adjList[i] = new LinkedList<>();
    }

    public void addEdge(int v, int w) {
        adjList[v].add(w);
    }

    public void BFS(int startVertex) {
        boolean[] visited = new boolean[V];
        LinkedList<Integer> queue = new LinkedList<>();

        visited[startVertex] = true;
        queue.add(startVertex);

        System.out.println("BFS traversal starting from vertex " + startVertex + ":");
        
        long startTime = System.nanoTime();
        
        while (!queue.isEmpty()) {
            startVertex = queue.poll();
            System.out.print(startVertex + " ");

            for (Integer neighbor : adjList[startVertex]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        
        System.out.println("\nTime taken: " + duration + " nanoseconds");
        System.out.println("Time Complexity: O(V + E) where V is vertices and E is edges");
    }

    public static void main(String[] args) {
        BFSImplementation graph = new BFSImplementation(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);

        graph.BFS(0);
    }
}