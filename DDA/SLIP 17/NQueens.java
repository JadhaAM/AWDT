// N-Queens Problem using Backtracking

public class NQueens {
    final int N;
    
    public NQueens(int n) {
        this.N = n;
    }
    
    /* A utility function to print solution */
    void printSolution(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(" " + board[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    /* A utility function to check if a queen can be placed on board[row][col] */
    boolean isSafe(int[][] board, int row, int col) {
        int i, j;
        
        // Check this row on left side
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
        for (i = row, j = col; j >= 0 && i < N; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        
        return true;
    }
    
    /* A recursive utility function to solve N Queen problem */
    boolean solveNQUtil(int[][] board, int col) {
        // Base case: If all queens are placed
        if (col >= N) {
            return true;
        }
        
        // Consider this column and try placing this queen in all rows one by one
        for (int i = 0; i < N; i++) {
            // Check if the queen can be placed on board[i][col]
            // Recur to place rest of the queens
                if (solveNQUtil(board, col + 1)) {
                    return true;
                }
                
                // If placing queen in board[i][col] doesn't lead to a solution
                // then remove queen from board[i][col]
                board[i][col] = 0; // BACKTRACK
            }
        }
        
        // If the queen cannot be placed in any row in this column col
        return false;
    }
    
    /* This function solves the N Queen problem using Backtracking */
    boolean solveNQ() {
        int[][] board = new int[N][N];
        
        if (!solveNQUtil(board, 0)) {
            System.out.println("Solution does not exist");
            return false;
        }
        
        System.out.println("Solution found:");
        printSolution(board);
        return true;
    }
    
    public static void main(String[] args) {
        NQueens queens = new NQueens(8); // Solve for 8 queens
        queens.solveNQ();
    }
