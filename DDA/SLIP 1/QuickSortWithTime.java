// Q2: Quick Sort with Time Calculation
import java.util.Random;
import java.util.Scanner;

public class QuickSortWithTime {
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
        
        quickSort(arr, 0, n - 1);
        
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
    
    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // pi is partitioning index
            int pi = partition(arr, low, high);
            
            // Recursively sort elements before and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    
    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // index of smaller element
        
        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                i++;
                
                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        
        return i + 1;
    }
}