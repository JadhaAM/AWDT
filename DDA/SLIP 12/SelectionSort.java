// Selection Sort 

import java.util.*;

public class SelectionSort {
    public void selectionSort(int[] arr) {
        int n = arr.length;
        
        long startTime = System.nanoTime();
        
        for (int i = 0; i < n-1; i++) {
            int minIdx = i;
            for (int j = i+1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            // Swap the found minimum element with the first element
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        
        System.out.println("Time taken: " + duration + " nanoseconds");
    }

    public static void main(String[] args) {
        SelectionSort sorter = new SelectionSort();
        
        // Test with different array sizes
        for (int size : new int[]{100, 1000, 10000}) {
            int[] arr = new int[size];
            Random rand = new Random();
            
            for (int i = 0; i < size; i++) {
                arr[i] = rand.nextInt(10000);
            }
            
            System.out.println("Sorting array of size " + size);
            sorter.selectionSort(arr.clone());
        }
    }
}