// Matrix Chain Multiplication 

public class MatrixChainMultiplication {
    public static int matrixChainOrder(int[] p) {
        int n = p.length - 1;
        int[][] m = new int[n][n];
        
        // Cost is zero when multiplying one matrix
        for (int i = 0; i < n; i++) {
            m[i][i] = 0;
        }
        
        // L is chain length
        for (int L = 2; L <= n; L++) {
            for (int i = 0; i < n - L + 1; i++) {
                int j = i + L - 1;
                m[i][j] = Integer.MAX_VALUE;
                
                for (int k = i; k < j; k++) {
                    int cost = m[i][k] + m[k + 1][j] + p[i] * p[k + 1] * p[j + 1];
                    if (cost < m[i][j]) {
                        m[i][j] = cost;
                    }
                }
            }
        }
        
        return m[0][n - 1];
    }
    
    public static void main(String[] args) {
        int[] dimensions = {10, 30, 5, 60};
        int minMultiplications = matrixChainOrder(dimensions);
        System.out.println("Minimum number of multiplications: " + minMultiplications);
    }
}