//  Dijkstra's Algorithm for Shortest Path 

import java.util.*;

public class DijkstraAlgorithm {
    private int V;
    private List<List<Node>> adjList;

    private static class Node {
        int vertex, weight;
        
        Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    public DijkstraAlgorithm(int v) {
        this.V = v;
        adjList = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, int weight) {
        adjList.get(source).add(new Node(destination, weight));
        adjList.get(destination).add(new Node(source, weight)); // For undirected graph
    }

    public void shortestPath(int sourceVertex) {
        int[] distance = new int[V];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[sourceVertex] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
        pq.add(new Node(sourceVertex, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;

            if (current.weight > distance[u]) continue;

            for (Node neighbor : adjList.get(u)) {
                int v = neighbor.vertex;
                int weight = neighbor.weight;

                if (distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                    pq.add(new Node(v, distance[v]));
                }
            }
        }

        System.out.println("Shortest distances from vertex " + sourceVertex + ":");
        for (int i = 0; i < V; i++) {
            System.out.println("To vertex " + i + ": " + distance[i]);
        }
    }

    public static void main(String[] args) {
        DijkstraAlgorithm graph = new DijkstraAlgorithm(6);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(2, 4, 3);
        graph.addEdge(3, 4, 2);
        graph.addEdge(3, 5, 1);
        graph.addEdge(4, 5, 6);

        graph.shortestPath(0);
    }
}