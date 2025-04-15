//  Sum of Subset by Backtracking 

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SumOfSubsets {
    private static boolean[] includeSet;
    private static int[] weights;
    private static int sum;
    private static int totalWeight;
    private static List<List<Integer>> solutions = new ArrayList<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        
        weights = new int[n + 1]; // 1-indexed for simplicity
        includeSet = new boolean[n + 1];
        
        System.out.println("Enter the elements:");
        for (int i = 1; i <= n; i++) {
            System.out.print("Element " + i + ": ");
            weights[i] = scanner.nextInt();
            totalWeight += weights[i];
        }
        
        System.out.print("Enter the target sum: ");
        sum = scanner.nextInt();
        
        if (sum > totalWeight) {
            System.out.println("No solution exists: target sum exceeds total weight");
        } else {
            System.out.println("\nFinding all subsets with sum = " + sum);
            sumOfSubsets(0, 1, totalWeight);
            
            if (solutions.isEmpty()) {
                System.out.println("No solution found");
            } else {
                System.out.println("\nFound " + solutions.size() + " solutions:");
                for (int i = 0; i < solutions.size(); i++) {
                    System.out.println("Solution " + (i + 1) + ": " + solutions.get(i));
                }
            }
        }
        
        scanner.close();
    }
    
    // Backtracking function to find subsets with given sum
    private static void sumOfSubsets(int currentSum, int k, int remainingWeight) {
        // If current subset sum equals target sum, we found a solution
        if (currentSum == sum) {
            List<Integer> solution = new ArrayList<>();
            for (int i = 1; i < includeSet.length; i++) {
                if (includeSet[i]) {
                    solution.add(weights[i]);
                }
            }
            solutions.add(solution);
            return;
        }
        
        // Generate candidates for next position
        if (k <= weights.length - 1 && currentSum + weights[k] <= sum && currentSum + remainingWeight >= sum) {
            // Include weights[k] in the solution
            includeSet[k] = true;
            sumOfSubsets(currentSum + weights[k], k + 1, remainingWeight - weights[k]);
            
            // Exclude weights[k] from the solution
            includeSet[k] = false;
            sumOfSubsets(currentSum, k + 1, remainingWeight - weights[k]);
        }
    }
}