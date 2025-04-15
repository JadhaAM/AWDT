
// Q2: Longest Common Subsequence Length
public class LongestCommonSubsequenceLength {
    // Function to find length of the Longest Common Subsequence of two strings
    static int lcs(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        
        // Create a table to store results of subproblems
        int[][] dp = new int[m + 1][n + 1];
        
        // Fill the dp table bottom-up
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // Base case: if either string is empty, LCS length is 0
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }
                // If characters match, increase LCS length by 1
                else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                // If characters don't match, take the maximum from possible subproblems
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        // Print DP table for clarity
        System.out.println("DP Table (LCS lengths for prefixes):");
        System.out.print("   ");
        for (int j = 0; j < n; j++) {
            System.out.print(" " + s2.charAt(j));
        }
        System.out.println();
        
        for (int i = 0; i <= m; i++) {
            if (i == 0) {
                System.out.print("  ");
            } else {
                System.out.print(s1.charAt(i - 1) + " ");
            }
            
            for (int j = 0; j <= n; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        
        // Return the length of LCS
        return dp[m][n];
    }
    
    public static void main(String[] args) {
        String s1 = "ABCBDAB";
        String s2 = "BDCABA";
        
        System.out.println("String 1: " + s1);
        System.out.println("String 2: " + s2);
        
        int lcsLength = lcs(s1, s2);
        
        System.out.println("\nLength of Longest Common Subsequence: " + lcsLength);
    }
}