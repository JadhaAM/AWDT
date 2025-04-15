//  DFS and BFS Comparison 

import java.util.*;

public class DFSBFSComparison {
    private int V; // Number of vertices
    private LinkedList<Integer>[] adjList;

    public DFSBFSComparison(int v) {
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

    public long DFS(int startVertex) {
        boolean[] visited = new boolean[V];
        System.out.println("DFS traversal starting from vertex " + startVertex + ":");
        
        long startTime = System.nanoTime();
        DFSUtil(startVertex, visited);
        long endTime = System.nanoTime();
        
        long duration = endTime - startTime;
        System.out.println("\nDFS Time: " + duration + " nanoseconds");
        return duration;
    }

    public long BFS(int startVertex) {
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
        long duration = endTime - startTime;
        
        System.out.println("\nBFS Time: " + duration + " nanoseconds");
        return duration;
    }

    public static void main(String[] args) {
        // Create a sample graph
        DFSBFSComparison graph = new DFSBFSComparison(1000);
        Random rand = new Random();
        
        // Add random edges
        for (int i = 0; i < 5000; i++) {
            int source = rand.nextInt(1000);
            int dest = rand.nextInt(1000);
            graph.addEdge(source, dest);
        }

        // Compare DFS and BFS times
        long dfsTime = graph.DFS(0);
        System.out.println();
        long bfsTime = graph.BFS(0);
        
        System.out.println("\nTime Complexity Comparison:");
        System.out.println("Both DFS and BFS have O(V + E) time complexity");
        System.out.println("DFS is " + (dfsTime < bfsTime ? "faster" : "slower") + " than BFS in this example");
    }
}