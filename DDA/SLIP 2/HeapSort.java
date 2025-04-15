
// Q1: Heap Sort
import java.util.Random;

public class HeapSort {
    public static void main(String[] args) {
        // Generate random elements
        int n = 10;
        int[] arr = new int[n];
        Random random = new Random();
        
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(100);
        }
        
        System.out.println("Original array:");
        printArray(arr);
        
        heapSort(arr);
        
        System.out.println("\nSorted array:");
        printArray(arr);
    }
    
    static void heapSort(int[] arr) {
        int n = arr.length;
        
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        
        // Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            
            // Call heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }
    
    static void heapify(int[] arr, int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // left = 2*i + 1
        int right = 2 * i + 2; // right = 2*i + 2
        
        // If left child is larger than root
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        
        // If right child is larger than largest so far
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        
        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            
            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }
    
    static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}

