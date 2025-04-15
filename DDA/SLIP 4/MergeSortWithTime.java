
// Q1: Merge Sort with Time Calculation
import java.util.Random;
import java.util.Scanner;

public class MergeSortWithTime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the number of elements:");
        int n = scanner.nextInt();
        
        int[] arr = new int[n];
        Random random = new Random();
        
        // Generate random elements
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(1000); // Random numbers between 0 and 999
        }
        
        System.out.println("Array with " + n + " random elements generated");
        
        // Measure time
        long startTime = System.nanoTime();
        
        mergeSort(arr, 0, n - 1);
        
        long endTime = System.nanoTime();
        
        System.out.println("Time taken to sort " + n + " elements: " + 
                          (endTime - startTime) / 1000000.0 + " milliseconds");
        
        // Print first few elements to verify sorting
        System.out.println("First few sorted elements:");
        for (int i = 0; i < Math.min(10, n); i++) {
            System.out.print(arr[i] + " ");
        }
        
        scanner.close();
    }
    
    // Merges two subarrays of arr[]
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    static void merge(int[] arr, int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
        
        // Create temp arrays
        int[] L = new int[n1];
        int[] R = new int[n2];
        
        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i) {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[m + 1 + j];
        }
        
        // Merge the temp arrays
        
        // Initial indices of first and second subarrays
        int i = 0, j = 0;
        
        // Initial index of merged subarray
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        
        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        
        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    
    // Main function that sorts arr[l..r] using merge()
    static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;
            
            // Sort first and second halves
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            
            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }
}

