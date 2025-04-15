// Implement optimal binary search tree 

import java.util.Scanner;

public class OptimalBinarySearchTree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter number of keys: ");
        int n = scanner.nextInt();
        
        double[] keys = new double[n + 1]; // 1-indexed for simplicity in algorithm
        double[] freq = new double[n + 1];
        
        System.out.println("Enter keys in sorted order:");
        for (int i = 1; i <= n; i++) {
            System.out.print("Key " + i + ": ");
            keys[i] = scanner.nextDouble();
        }
        
        System.out.println("Enter frequencies for each key:");
        for (int i = 1; i <= n; i++) {
            System.out.print("Frequency for key " + keys[i] + ": ");
            freq[i] = scanner.nextDouble();
        }
        
        double cost = optimalBST(keys, freq, n);
        System.out.println("\nCost of optimal BST: " + cost);
        
        scanner.close();
    }
    
    // Function to calculate optimal BST
    public static double optimalBST(double[] keys, double[] freq, int n) {
        // Create tables for results
        double[][] cost = new double[n + 1][n + 1];
        double[][] root = new double[n + 1][n + 1];
        double[] sumFreq = new double[n + 1];
        
        // Initialize cost for single key trees (diagonal elements)
        for (int i = 1; i <= n; i++) {
            cost[i][i] = freq[i];
            root[i][i] = i;
            sumFreq[i] = freq[i];
        }
        
        // Calculate cumulative frequencies
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                sumFreq[j] += freq[j];
            }
        }
        
        // Calculate cost for trees with multiple keys
        for (int l = 2; l <= n; l++) { // l is the length of subarray
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                cost[i][j] = Double.MAX_VALUE;
                
                // Try making each key as root
                for (int r = i; r <= j; r++) {
                    double c = ((r > i) ? cost[i][r - 1] : 0) + 
                               ((r < j) ? cost[r + 1][j] : 0) + 
                               sum(freq, i, j);
                    
                    if (c < cost[i][j]) {
                        cost[i][j] = c;
                        root[i][j] = r;
                    }
                }
            }
        }
        
        System.out.println("\nRoot of optimal BST: " + (int)root[1][n]);
        printOptimalBST(root, 1, n, 0, "Root");
        
        return cost[1][n];
    }
    
    // Helper function to calculate sum of frequencies from i to j
    private static double sum(double[] freq, int i, int j) {
        double s = 0;
        for (int k = i; k <= j; k++) {
            s += freq[k];
        }
        return s;
    }
    
    // Print the structure of the optimal BST
    private static void printOptimalBST(double[][] root, int i, int j, int level, String position) {
        if (i > j) return;
        
        int r = (int)root[i][j];
        System.out.println("Level " + level + ", " + position + ": Key " + r);
        
        printOptimalBST(root, i, r - 1, level + 1, "Left of " + r);
        printOptimalBST(root, r + 1, j, level + 1, "Right of " + r);
    }
}