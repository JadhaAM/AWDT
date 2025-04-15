// Optimal Binary Search Tree 

public class OptimalBST {
    public static void optimalBST(double[] p, double[] q, int n) {
        double[][] e = new double[n+2][n+1];
        double[][] w = new double[n+2][n+1];
        int[][] root = new int[n+1][n+1];
        
        // Initialize e and w for subproblems of length 0
        for (int i = 1; i <= n+1; i++) {
            e[i][i-1] = q[i-1];
            w[i][i-1] = q[i-1];
        }
        
        // Compute costs for subproblems of length 1 to n
        for (int l = 1; l <= n; l++) {
            for (int i = 1; i <= n-l+1; i++) {
                int j = i + l - 1;
                e[i][j] = Double.POSITIVE_INFINITY;
                w[i][j] = w[i][j-1] + p[j] + q[j];
                
                // Find root that minimizes cost
                for (int r = i; r <= j; r++) {
                    double t = e[i][r-1] + e[r+1][j] + w[i][j];
                    if (t < e[i][j]) {
                        e[i][j] = t;
                        root[i][j] = r;
                    }
                }
            }
        }
        
        System.out.println("Expected cost of optimal BST: " + e[1][n]);
        System.out.println("Best case complexity: O(n) when all keys have equal frequency");
        System.out.println("Worst case complexity: O(n^3) for the algorithm implementation");
    }
    
    public static void main(String[] args) {
        // Example with 4 keys
        double[] p = {0, 0.15, 0.10, 0.05, 0.10}; // p[1] to p[n] are frequencies of keys
        double[] q = {0.05, 0.10, 0.05, 0.05, 0.10}; // q[0] to q[n] are frequencies of dummy keys
        
        optimalBST(p, q, 4);
    }
}