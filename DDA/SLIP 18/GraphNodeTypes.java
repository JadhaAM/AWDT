// Finding Live, E, and Dead Nodes in a Graph 

import java.util.*;

public class GraphNodeTypes {
    private int V;
    private List<Integer>[] adjList;
    private boolean[] visited;
    private boolean[] inStack;
    
    public GraphNodeTypes(int v) {
        this.V = v;
        adjList = new List[v];
        visited = new boolean[v];
        inStack = new boolean[v];
        
        for (int i = 0; i < v; i++) {
            adjList[i] = new ArrayList<>();
        }
    }
    
    public void addEdge(int v, int w) {
        adjList[v].add(w);
    }
    
    public void findNodeTypes() {
        Set<Integer> liveNodes = new HashSet<>();
        Set<Integer> eNodes = new HashSet<>();
        Set<Integer> deadNodes = new HashSet<>();
        
        // First DFS to identify E-nodes (nodes on cycle) and mark visited
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                findENodes(i, liveNodes, eNodes);
            }
        }
        
        // Reset visited array for second pass
        Arrays.fill(visited, false);
        
        // Second pass to identify dead nodes
        // A node is dead if it can reach an E-node
        for (int i = 0; i < V; i++) {
            if (!visited[i] && !eNodes.contains(i)) {
                if (canReachENode(i, eNodes)) {
                    deadNodes.add(i);
                } else {
                    liveNodes.add(i);
                }
            }
        }
        
        // Print results
        System.out.println("Live nodes: " + liveNodes);
        System.out.println("E-nodes: " + eNodes);
        System.out.println("Dead nodes: " + deadNodes);
    }
    
    private boolean findENodes(int v, Set<Integer> liveNodes, Set<Integer> eNodes) {
        visited[v] = true;
        inStack[v] = true;
        
        boolean foundCycle = false;
        
        for (int neighbor : adjList[v]) {
            if (!visited[neighbor]) {
                if (findENodes(neighbor, liveNodes, eNodes)) {
                    eNodes.add(v);
                    foundCycle = true;
                }
            } else if (inStack[neighbor]) {
                // Found a back edge, indicating a cycle
                eNodes.add(v);
                foundCycle = true;
            }
        }
        
        inStack[v] = false;
        return foundCycle;
    }
    
    private boolean canReachENode(int v, Set<Integer> eNodes) {
        visited[v] = true;
        
        // If this node is an E-node, we found what we're looking for
        if (eNodes.contains(v)) {
            return true;
        }
        
        // Check all neighbors
        for (int neighbor : adjList[v]) {
            if (!visited[neighbor]) {
                if (canReachENode(neighbor, eNodes)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        GraphNodeTypes g = new GraphNodeTypes(7);
        
        // Create a graph with some cycles
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 0); // Cycle: 0->1->2->0
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 3); // Cycle: 3->4->3
        g.addEdge(5, 6);
        
        g.findNodeTypes();
    }
}