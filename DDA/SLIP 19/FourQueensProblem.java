//  4-Queens Problem Board Configuration

 public class FourQueensProblem {
    static final int N = 4;
    
    // Function to print the board configuration
    private static void printBoard(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1)
                    System.out.print(" Q ");
                else
                    System.out.print(" - ");
            }
            System.out.println();
        }
    }
    
    // Function to check if a queen can be placed at board[row][col]
    private static boolean isSafe(int[][] board, int row, int col) {
        int i, j;
        
        // Check this row on left side
        for (i = 0; i < col; i++) {
            if (board[row][i] == 1)
                return false;
        }
        
        // Check upper diagonal on left side
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1)
                return false;
        }
        
        // Check lower diagonal on left side
        for (i = row, j = col; j >= 0 && i < N; i++, j--) {
            if (board[i][j] == 1)
                return false;
        }
        
        return true;
    }
    
    // A recursive function to solve the 4-Queens problem
    private static boolean solveNQueens(int[][] board, int col) {
        // Base case: If all queens are placed
        if (col >= N)
            return true;
        
        // Try placing queen in each row of this column
        for (int i = 0; i < N; i++) {
            // Check if queen can be placed on board[i][col]
            if (isSafe(board, i, col)) {
                // Place the queen
                board[i][col] = 1;
                
                // Recur to place the rest of the queens
                if (solveNQueens(board, col + 1))
                    return true;
                
                // If placing queen at board[i][col] doesn't lead to a solution
                // then remove the queen from board[i][col]
                board[i][col] = 0; // Backtrack
            }
        }
        
        // If queen cannot be placed in any row in this column
        return false;
    }
    
    public static void main(String[] args) {
        int[][] board = new int[N][N];
        
        if (solveNQueens(board, 0)) {
            System.out.println("Solution exists. Board configuration:");
            printBoard(board);
        } else {
            System.out.println("Solution does not exist.");
        }
    }
}