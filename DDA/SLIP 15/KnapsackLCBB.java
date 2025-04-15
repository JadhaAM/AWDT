// 0/1 Knapsack using LCBB (Least Cost Branch and Bound) 

import java.util.*;

public class KnapsackLCBB {
    static class Item {
        int weight;
        int value;
        double ratio; // value per unit weight
        
        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
            this.ratio = (double) value / weight;
        }
    }
    
    static class Node {
        int level;
        int profit;
        int weight;
        double bound;
        
        public Node(int level, int profit, int weight) {
            this.level = level;
            this.profit = profit;
            this.weight = weight;
        }
    }
    
    public static int knapsackLCBB(Item[] items, int capacity) {
        // Sort items by value/weight ratio in descending order
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));
        
        // Create a priority queue (min heap) based on the negative of bound value
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Double.compare(b.bound, a.bound));
        
        // Create a dummy node of decision tree
        Node u = new Node(-1, 0, 0);
        u.bound = bound(u, items, capacity);
        pq.add(u);
        
        int maxProfit = 0;
        
        while (!pq.isEmpty()) {
            // Extract an item from priority queue
            u = pq.poll();
            
            // If it is the starting node, assign level 0
            if (u.level == -1)
                u.level = 0;
            
            // If nothing more to put, return
            if (u.level == items.length)
                continue;
            
            // Take the next item
            Node v = new Node(u.level + 1, u.profit + items[u.level].value, 
                            u.weight + items[u.level].weight);
            
            // If cumulated weight is less than capacity and profit is greater than max profit so far
            if (v.weight <= capacity && v.profit > maxProfit)
                maxProfit = v.profit;
            
            // Get the upper bound on profit to decide whether to add v to Q or not
            v.bound = bound(v, items, capacity);
            
            if (v.bound > maxProfit)
                pq.add(v);
            
            // Do the same thing, but without taking the item
            v = new Node(u.level + 1, u.profit, u.weight);
            v.bound = bound(v, items, capacity);
            
            if (v.bound > maxProfit)
                pq.add(v);
        }
        
        return maxProfit;
    }
    
    private static double bound(Node u, Item[] items, int capacity) {
        // If weight exceeds capacity, return 0 as bound
        if (u.weight >= capacity)
            return 0;
        
        // Initialize bound value with current profit
        double profitBound = u.profit;
        int j = u.level + 1;
        int totalWeight = u.weight;
        
        // Check index bounds and weight constraint
        while (j < items.length && totalWeight + items[j].weight <= capacity) {
            totalWeight += items[j].weight;
            profitBound += items[j].value;
            j++;
        }
        
        // If we still have capacity, add fraction of the next item
        if (j < items.length)
            profitBound += (capacity - totalWeight) * items[j].ratio;
        
        return profitBound;
    }
    
    public static void main(String[] args) {
        Item[] items = {
            new Item(10, 60),
            new Item(20, 100),
            new Item(30, 120)
        };
        int capacity = 50;
        
        int maxProfit = knapsackLCBB(items, capacity);
        System.out.println("Maximum value in Knapsack = " + maxProfit);
    }
}