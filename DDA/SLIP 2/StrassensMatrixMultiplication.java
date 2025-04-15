// Q2: Strassen's Matrix Multiplication
public class StrassensMatrixMultiplication {
    public static void main(String[] args) {
        // Example matrices
        int[][] A = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };
        
        int[][] B = {
            {17, 18, 19, 20},
            {21, 22, 23, 24},
            {25, 26, 27, 28},
            {29, 30, 31, 32}
        };
        
        System.out.println("Matrix A:");
        printMatrix(A);
        
        System.out.println("\nMatrix B:");
        printMatrix(B);
        
        int[][] result = strassenMultiply(A, B);
        
        System.out.println("\nResult Matrix (A*B):");
        printMatrix(result);
    }
    
    // Pad matrix to nearest power of 2
    static int[][] padMatrix(int[][] matrix, int size) {
        int[][] result = new int[size][size];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result[i][j] = matrix[i][j];
            }
        }
        return result;
    }
    
    // Get next power of 2
    static int nextPowerOfTwo(int n) {
        int power = 1;
        while (power < n) {
            power *= 2;
        }
        return power;
    }
    
    // Main Strassen's multiplication method
    static int[][] strassenMultiply(int[][] A, int[][] B) {
        int n = A.length;
        
        // If n is not a power of 2, pad the matrices
        int originalSize = n;
        if ((n & (n - 1)) != 0) {
            // n is not a power of 2
            n = nextPowerOfTwo(n);
            A = padMatrix(A, n);
            B = padMatrix(B, n);
        }
        
        // Perform Strassen's multiplication
        int[][] result = strassenRecursive(A, B);
        
        // Create result of original size
        int[][] finalResult = new int[originalSize][originalSize];
        for (int i = 0; i < originalSize; i++) {
            for (int j = 0; j < originalSize; j++) {
                finalResult[i][j] = result[i][j];
            }
        }
        
        return finalResult;
    }
    
    // Recursive Strassen's multiplication
    static int[][] strassenRecursive(int[][] A, int[][] B) {
        int n = A.length;
        
        if (n == 1) {
            int[][] C = new int[1][1];
            C[0][0] = A[0][0] * B[0][0];
            return C;
        }
        
        int newSize = n / 2;
        
        // Dividing matrices into submatrices
        int[][] A11 = new int[newSize][newSize];
        int[][] A12 = new int[newSize][newSize];
        int[][] A21 = new int[newSize][newSize];
        int[][] A22 = new int[newSize][newSize];
        
        int[][] B11 = new int[newSize][newSize];
        int[][] B12 = new int[newSize][newSize];
        int[][] B21 = new int[newSize][newSize];
        int[][] B22 = new int[newSize][newSize];
        
        // Dividing matrix A into 4 parts
        divideMatrix(A, A11, 0, 0);
        divideMatrix(A, A12, 0, newSize);
        divideMatrix(A, A21, newSize, 0);
        divideMatrix(A, A22, newSize, newSize);
        
        // Dividing matrix B into 4 parts
        divideMatrix(B, B11, 0, 0);
        divideMatrix(B, B12, 0, newSize);
        divideMatrix(B, B21, newSize, 0);
        divideMatrix(B, B22, newSize, newSize);
        
        // M1 = (A11 + A22) * (B11 + B22)
        int[][] M1 = strassenRecursive(
            addMatrices(A11, A22), addMatrices(B11, B22)
        );
        
        // M2 = (A21 + A22) * B11
        int[][] M2 = strassenRecursive(
            addMatrices(A21, A22), B11
        );
        
        // M3 = A11 * (B12 - B22)
        int[][] M3 = strassenRecursive(
            A11, subtractMatrices(B12, B22)
        );
        
        // M4 = A22 * (B21 - B11)
        int[][] M4 = strassenRecursive(
            A22, subtractMatrices(B21, B11)
        );
        
        // M5 = (A11 + A12) * B22
        int[][] M5 = strassenRecursive(
            addMatrices(A11, A12), B22
        );
        
        // M6 = (A21 - A11) * (B11 + B12)
        int[][] M6 = strassenRecursive(
            subtractMatrices(A21, A11), addMatrices(B11, B12)
        );
        
        // M7 = (A12 - A22) * (B21 + B22)
        int[][] M7 = strassenRecursive(
            subtractMatrices(A12, A22), addMatrices(B21, B22)
        );
        
        // C11 = M1 + M4 - M5 + M7
        int[][] C11 = addMatrices(
            subtractMatrices(
                addMatrices(M1, M4), M5
            ), M7
        );
        
        // C12 = M3 + M5
        int[][] C12 = addMatrices(M3, M5);
        
        // C21 = M2 + M4
        int[][] C21 = addMatrices(M2, M4);
        
        // C22 = M1 - M2 + M3 + M6
        int[][] C22 = addMatrices(
            addMatrices(
                subtractMatrices(M1, M2), M3
            ), M6
        );
        
        // Combining the C matrices into a single matrix
        int[][] C = new int[n][n];
        copySubMatrix(C11, C, 0, 0);
        copySubMatrix(C12, C, 0, newSize);
        copySubMatrix(C21, C, newSize, 0);
        copySubMatrix(C22, C, newSize, newSize);
        
        return C;
    }
    
    // Helper method to divide matrices
    static void divideMatrix(int[][] parent, int[][] child, int iB, int jB) {
        int childSize = child.length;
        
        for (int i1 = 0, i2 = iB; i1 < childSize; i1++, i2++) {
            for (int j1 = 0, j2 = jB; j1 < childSize; j1++, j2++) {
                child[i1][j1] = parent[i2][j2];
            }
        }
    }
    
    // Helper method to copy submatrix back
    static void copySubMatrix(int[][] child, int[][] parent, int iB, int jB) {
        int childSize = child.length;
        
        for (int i1 = 0, i2 = iB; i1 < childSize; i1++, i2++) {
            for (int j1 = 0, j2 = jB; j1 < childSize; j1++, j2++) {
                parent[i2][j2] = child[i1][j1];
            }
        }
    }
    
    // Add two matrices
    static int[][] addMatrices(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        
        return C;
    }
    
    // Subtract two matrices
    static int[][] subtractMatrices(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        
        return C;
    }
    
    // Print a matrix
    static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}