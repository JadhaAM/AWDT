// Q2: N-Queens Problem using Backtracking 

public class NQueensBacktracking {
    private final int N;
    
    public NQueensBacktracking(int n) {
        this.N = n;
    }

    private void printSolution(int[][] board) {
        System.out.println("N-Queens Solution (N=" + N + "):");
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
  
    private boolean isSafe(int[][] board, int row, int col) {
        int i, j;
        for (i = 0; i < col; i++) {
            if (board[row][i] == 1)
                return false;
        }

        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1)
                return false;
        }
        for (i = row, j = col; j >= 0 && i < N; i++, j--) {
            if (board[i][j] == 1)
                return false;
        }
        
        return true;
    }
    private boolean solveNQueensUtil(int[][] board, int col) {
        if (col >= N)
            return true;
        for (int i = 0; i < N; i++) {
            if (isSafe(board, i, col)) {
                board[i][col] = 1;
                if (solveNQueensUtil(board, col + 1))
                    return true;
                board[i][col] = 0; // Backtrack
            }
        }
        
        // If queen cannot be placed in any row in this column
        return false;
    }
    
    public boolean solveNQueens() {
        int[][] board = new int[N][N];
        
        if (!solveNQueensUtil(board, 0)) {
            System.out.println("Solution does not exist for N = " + N);
            return false;
        }
        
        printSolution(board);
        return true;
    }
    
    public static void main(String[] args) {
        int n = 8; 
        NQueensBacktracking queensProblem = new NQueensBacktracking(n);
        queensProblem.solveNQueens();
    }
}