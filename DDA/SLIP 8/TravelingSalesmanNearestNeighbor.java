// Traveling Salesman Problem using Nearest Neighbor Algorithm 

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TravelingSalesmanNearestNeighbor {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter number of cities: ");
        int n = scanner.nextInt();
        
        // Distance matrix between cities
        double[][] distances = new double[n][n];
        
        System.out.println("\nEnter the distance matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    distances[i][j] = 0; // Distance to self is 0
                } else {
                    System.out.print("Distance from city " + i + " to city " + j + ": ");
                    distances[i][j] = scanner.nextDouble();
                }
            }
        }
        
        System.out.print("\nEnter starting city (0 to " + (n-1) + "): ");
        int startCity = scanner.nextInt();
        
        // Solve TSP using nearest neighbor
        List<Integer> tour = findTourNearestNeighbor(distances, startCity);
        double tourCost = calculateTourCost(distances, tour);
        
        // Print the results
        System.out.println("\nTour using Nearest Neighbor Algorithm:");
        for (int city : tour) {
            System.out.print(city + " -> ");
        }
        System.out.println(tour.get(0) + " (back to start)");
        System.out.println("Total distance: " + tourCost);
        
        scanner.close();
    }
    
    // Find a TSP tour using nearest neighbor heuristic
    public static List<Integer> findTourNearestNeighbor(double[][] distances, int startCity) {
        int n = distances.length;
        List<Integer> tour = new ArrayList<>();
        boolean[] visited = new boolean[n];
        
        // Start from the given city
        tour.add(startCity);
        visited[startCity] = true;
        
        // Add n-1 more cities
        for (int i = 1; i < n; i++) {
            int lastCity = tour.get(tour.size() - 1);
            int nextCity = findNearestCity(distances, lastCity, visited);
            tour.add(nextCity);
            visited[nextCity] = true;
        }
        
        return tour;
    }
    
    // Find the nearest unvisited city
    private static int findNearestCity(double[][] distances, int currentCity, boolean[] visited) {
        int n = distances.length;
        int nearestCity = -1;
        double minDistance = Double.MAX_VALUE;
        
        for (int city = 0; city < n; city++) {
            if (!visited[city] && distances[currentCity][city] < minDistance) {
                minDistance = distances[currentCity][city];
                nearestCity = city;
            }
        }
        
        return nearestCity;
    }
    
    // Calculate the total cost of a tour
    private static double calculateTourCost(double[][] distances, List<Integer> tour) {
        double cost = 0;
        int n = tour.size();
        
        for (int i = 0; i < n - 1; i++) {
            cost += distances[tour.get(i)][tour.get(i + 1)];
        }
        
        // Add return to starting city
        cost += distances[tour.get(n - 1)][tour.get(0)];
        
        return cost;
    }
}