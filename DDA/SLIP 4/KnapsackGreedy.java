// Q2: Knapsack Problem using Greedy Method
import java.util.Arrays;
import java.util.Comparator;

public class KnapsackGreedy {
    // Item class to store item details
    static class Item {
        int value;
        int weight;
        double ratio; // value per unit weight
        
        public Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
            this.ratio = (double) value / weight;
        }
    }
    
    // Greedy method to solve Knapsack problem
    static double getMaxValue(Item[] items, int capacity) {
        // Sort items based on value/weight ratio
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                return Double.compare(item2.ratio, item1.ratio);
            }
        });
        
        double totalValue = 0;
        int currentWeight = 0;
        
        for (Item item : items) {
            // If adding item won't overflow, add it completely
            if (currentWeight + item.weight <= capacity) {
                currentWeight += item.weight;
                totalValue += item.value;
                System.out.println("Added item with weight " + item.weight + 
                                   " and value " + item.value + " completely.");
            } else {
                // If we can't add it completely, add fractional part
                int remainingCapacity = capacity - currentWeight;
                totalValue += item.value * ((double) remainingCapacity / item.weight);
                System.out.println("Added " + ((double) remainingCapacity / item.weight) * 100 + 
                                   "% of item with weight " + item.weight + 
                                   " and value " + item.value);
                break;
            }
        }
        
        return totalValue;
    }
    
    public static void main(String[] args) {
        Item[] items = {
            new Item(60, 10), 
            new Item(100, 20), 
            new Item(120, 30)
        };
        
        int capacity = 50;
        
        System.out.println("Items available:");
        for (int i = 0; i < items.length; i++) {
            System.out.println("Item " + (i+1) + ": Value = " + items[i].value + 
                              ", Weight = " + items[i].weight + 
                              ", Value/Weight = " + items[i].ratio);
        }
        
        System.out.println("\nKnapsack capacity: " + capacity);
        
        double maxValue = getMaxValue(items, capacity);
        
        System.out.println("\nMaximum value obtained: " + maxValue);
    }
}