// DFS (Depth First Search) Implementation 

import java.util.*;

public class DFSImplementation {
    private int V; // Number of vertices
    private LinkedList<Integer>[] adjList;

    public DFSImplementation(int v) {
        V = v;
        adjList = new LinkedList[v];
        for (int i = 0; i < v; i++)
            adjList[i] = new LinkedList<>();
    }

    public void addEdge(int v, int w) {
        adjList[v].add(w);
    }

    private void DFSUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");

        for (Integer neighbor : adjList[v]) {
            if (!visited[neighbor])
                DFSUtil(neighbor, visited);
        }
    }

    public void DFS(int startVertex) {
        boolean[] visited = new boolean[V];
        System.out.println("DFS traversal starting from vertex " + startVertex + ":");
        DFSUtil(startVertex, visited);
        System.out.println("\nTime Complexity: O(V + E) where V is vertices and E is edges");
    }

    public static void main(String[] args) {
        DFSImplementation graph = new DFSImplementation(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);

        graph.DFS(0);
    }
}