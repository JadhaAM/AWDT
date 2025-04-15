// Fractional Knapsack using Greedy Method 

import java.util.Arrays;
import java.util.Scanner;

public class FractionalKnapsack {
    // Item class to store weight and value of items
    static class Item implements Comparable<Item> {
        double weight;
        double value;
        double ratio; // value per unit weight

        public Item(double weight, double value) {
            this.weight = weight;
            this.value = value;
            this.ratio = value / weight;
        }

        // Sort by ratio in descending order
        @Override
        public int compareTo(Item other) {
            return Double.compare(other.ratio, this.ratio);
        }
    }

    // Function to solve fractional knapsack
    public static double getMaxValue(Item[] items, double capacity) {
        // Sort items by value/weight ratio in descending order
        Arrays.sort(items);
        
        double totalValue = 0.0;
        double remainingWeight = capacity;

        // Greedy approach: Take items with highest value/weight ratio first
        for (Item item : items) {
            // If we can take the whole item, take it
            if (remainingWeight >= item.weight) {
                totalValue += item.value;
                remainingWeight -= item.weight;
            } else {
                // Take a fraction of the item
                double fraction = remainingWeight / item.weight;
                totalValue += fraction * item.value;
                break; // Knapsack is full
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter number of items: ");
        int n = scanner.nextInt();
        
        Item[] items = new Item[n];
        
        System.out.println("Enter weight and value for each item:");
        for (int i = 0; i < n; i++) {
            System.out.print("Weight of item " + (i+1) + ": ");
            double weight = scanner.nextDouble();
            System.out.print("Value of item " + (i+1) + ": ");
            double value = scanner.nextDouble();
            items[i] = new Item(weight, value);
        }
        
        System.out.print("Enter knapsack capacity: ");
        double capacity = scanner.nextDouble();
        
        double maxValue = getMaxValue(items, capacity);
        System.out.println("\nMaximum value in knapsack = " + maxValue);
        
        // Print which items were selected and their fractions
        System.out.println("\nItems selected:");
        double remainingWeight = capacity;
        for (int i = 0; i < n && remainingWeight > 0; i++) {
            Item item = items[i];
            if (remainingWeight >= item.weight) {
                System.out.println("Item " + (i+1) + ": 100%");
                remainingWeight -= item.weight;
            } else {
                double fraction = remainingWeight / item.weight * 100;
                System.out.println("Item " + (i+1) + ": " + fraction + "%");
                remainingWeight = 0;
            }
        }
        
        scanner.close();
    }
}