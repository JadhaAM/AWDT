// 4 Queens Problem using Backtracking 

public class FourQueensProblem {
    private static final int N = 4;
    private static int[][] board = new int[N][N];
    private static int solutionCount = 0;
    
    public static void main(String[] args) {
        System.out.println("Solving 4 Queens Problem using Backtracking\n");
        
        // Initialize the board with zeros (no queens)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = 0;
            }
        }
        
        if (solveNQueens(0)) {
            System.out.println("Solutions found: " + solutionCount);
        } else {
            System.out.println("No solution exists");
        }
    }
    
    // Function to check if a queen can be placed at board[row][col]
    private static boolean isSafe(int row, int col) {
        int i, j;
        
        // Check the row on the left side
        for (i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }
        
        // Check upper diagonal on left side
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        
        // Check lower diagonal on left side
        for (i = row, j = col; i < N && j >= 0; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        
        return true;
    }
    
    // A recursive utility function to solve N Queens problem
    private static boolean solveNQueens(int col) {
        // Base case: If all queens are placed
        if (col >= N) {
            printSolution();
            solutionCount++;
            // Return false to find all solutions
            return false;
        }
        
        boolean res = false;
        
        // Try placing a queen in all rows of this column
        for (int i = 0; i < N; i++) {
            // Check if queen can be placed at board[i][col]
            if (isSafe(i, col)) {
                // Place the queen
                board[i][col] = 1;
                
                // Recur to place rest of the queens
                res = solveNQueens(col + 1) || res;
                
                // Backtrack
                board[i][col] = 0;
            }
        }
        
        return res;
    }
    
    // Print the solution
    private static void printSolution() {
        System.out.println("Solution " + (solutionCount + 1) + ":");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}