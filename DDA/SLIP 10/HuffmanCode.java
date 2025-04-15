//  Huffman Code using greedy methods 

import java.util.*;

public class HuffmanCode {
    // Node class for Huffman tree
    static class Node implements Comparable<Node> {
        char ch;
        int freq;
        Node left, right;
        
        public Node(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
            left = right = null;
        }
        
        public Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.freq - o.freq;
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the string to encode: ");
        String text = scanner.nextLine();
        
        // Calculate frequency of each character
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        
        // Build Huffman tree
        Node root = buildHuffmanTree(freqMap);
        
        // Generate codes from tree
        Map<Character, String> codes = generateCodes(root);
        
        // Display the codes
        System.out.println("\nHuffman Codes:");
        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            System.out.println("'" + entry.getKey() + "': " + entry.getValue());
        }
        
        // Encode the original string
        StringBuilder encodedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            encodedText.append(codes.get(c));
        }
        
        System.out.println("\nEncoded text: " + encodedText.toString());
        
        // Calculate compression ratio
        int originalBits = text.length() * 8; // Assuming 8 bits per character
        int compressedBits = encodedText.length();
        double compressionRatio = (double) originalBits / compressedBits;
        
        System.out.println("\nOriginal size (bits): " + originalBits);
        System.out.println("Compressed size (bits): " + compressedBits);
        System.out.println("Compression ratio: " + compressionRatio);
        
        System.out.println("\nComplexity Analysis:");
        System.out.println("Best case: O(n log n) where n is the number of unique characters");
        System.out.println("Worst case: O(n log n) where n is the number of unique characters");
        
        scanner.close();
    }
    
    // Build Huffman Tree using a priority queue
    private static Node buildHuffmanTree(Map<Character, Integer> freqMap) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        
        // Create leaf nodes for each character and add to priority queue
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            minHeap.add(new Node(entry.getKey(), entry.getValue()));
        }
        
        // Build Huffman tree by combining two nodes with lowest frequencies
        while (minHeap.size() > 1) {
            Node left = minHeap.poll();
            Node right = minHeap.poll();
            
            // Create internal node with these two nodes as children
            // We use '\0' for internal nodes as they don't represent any character
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            minHeap.add(parent);
        }
        
        // Root of Huffman tree
        return minHeap.poll();
    }
    
    // Generate Huffman codes for each character
    private static Map<Character, String> generateCodes(Node root) {
        Map<Character, String> codes = new HashMap<>();
        generateCodesRecursive(root, "", codes);
        return codes;
    }
    
    private static void generateCodesRecursive(Node node, String code, Map<Character, String> codes) {
        if (node == null) {
            return;
        }
        
        // If this is a leaf node, it contains a character
        if (node.left == null && node.right == null) {
            codes.put(node.ch, code.length() > 0 ? code : "1"); // For single character strings
        }
        
        // Traverse left (add 0)
        generateCodesRecursive(node.left, code + "0", codes);
        
        // Traverse right (add 1)
        generateCodesRecursive(node.right, code + "1", codes);
    }
}