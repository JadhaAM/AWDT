// Q2: Huffman Coding with Complexity Analysis
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCoding {
    // Node class for Huffman Tree
    static class HuffmanNode {
        char character;
        int frequency;
        HuffmanNode left;
        HuffmanNode right;
        
        public HuffmanNode(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
            this.left = null;
            this.right = null;
        }
        
        public HuffmanNode(char character, int frequency, HuffmanNode left, HuffmanNode right) {
            this.character = character;
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }
        
        // Check if the node is a leaf node
        boolean isLeaf() {
            return left == null && right == null;
        }
    }
    
    // Build Huffman Tree
    static HuffmanNode buildHuffmanTree(String text) {
        // Count frequency of each character
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        
        // Create a priority queue to store nodes
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(
            new Comparator<HuffmanNode>() {
                @Override
                public int compare(HuffmanNode a, HuffmanNode b) {
                    return a.frequency - b.frequency;
                }
            }
        );
        
        // Create leaf nodes and add them to the priority queue
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }
        
        // Build Huffman Tree: remove two nodes with the lowest frequency,
        // create a new internal node with these two nodes as children,
        // and add the new node to the priority queue.
        // Repeat until only one node remains.
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            
            // '$' is a special value for internal nodes, not used in the input text
            HuffmanNode parent = new HuffmanNode('$', left.frequency + right.frequency, left, right);
            pq.add(parent);
        }
        
        // The remaining node is the root of the Huffman Tree
        return pq.poll();
    }
    
    // Traverse the Huffman Tree and generate codes
    static void generateCodes(HuffmanNode root, String code, Map<Character, String> huffmanCodes) {
        if (root == null) {
            return;
        }
        
        // If this is a leaf node, store the code
        if (root.isLeaf()) {
            huffmanCodes.put(root.character, code.length() > 0 ? code : "1");
            return;
        }
        
        // Traverse left with code + "0"
        generateCodes(root.left, code + "0", huffmanCodes);
        
        // traverse right with code + "1"
        generateCodes(root.right, code + "1", huffmanCodes);
    }
    
    // Encode the input text
    static String encode(String text, Map<Character, String> huffmanCodes) {
        StringBuilder result = new StringBuilder();
        
        for (char c : text.toCharArray()) {
            result.append(huffmanCodes.get(c));
        }
        
        return result.toString();
    }
    
    // Decode the encoded string using Huffman Tree
    static String decode(String encodedText, HuffmanNode root) {
        StringBuilder result = new StringBuilder();
        HuffmanNode current = root;
        
        for (int i = 0; i < encodedText.length(); i++) {
            // Traverse the Huffman Tree
            if (encodedText.charAt(i) == '0') {
                current = current.left;
            } else {
                current = current.right;
            }
            
            // If we reach a leaf node, append its character to result
            // and restart from the root
            if (current.isLeaf()) {
                result.append(current.character);
                current = root;
            }
        }
        
        return result.toString();
    }
    
    public static void main(String[] args) {
        String text = "this is an example for huffman encoding";
        System.out.println("Original Text: " + text);
        
        // Build Huffman Tree
        HuffmanNode root = buildHuffmanTree(text);
        
        // Generate Huffman codes
        Map<Character, String> huffmanCodes = new HashMap<>();
        generateCodes(root, "", huffmanCodes);
        
        // Print Huffman codes
        System.out.println("\nHuffman Codes:");
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.println("'" + entry.getKey() + "' : " + entry.getValue());
        }
        
        // Encode the text
        String encodedText = encode(text, huffmanCodes);
        System.out.println("\nEncoded Text: " + encodedText);
        
        // Decode the text
        String decodedText = decode(encodedText, root);
        System.out.println("\nDecoded Text: " + decodedText);
        
        // Calculate compression ratio
        int originalBits = text.length() * 8; // Assuming 8 bits per character
        int compressedBits = encodedText.length();
        double compressionRatio = (double) originalBits / compressedBits;
        
        System.out.println("\nOriginal size (bits): " + originalBits);
        System.out.println("Compressed size (bits): " + compressedBits);
        System.out.println("Compression ratio: " + compressionRatio);
        
        // Complexity Analysis
        System.out.println("\nComplexity Analysis:");
        System.out.println("Best Case Time Complexity: O(n log n)");
        System.out.println("- n is the number of unique characters");
        System.out.println("- Building the frequency table takes O(n) time");
        System.out.println("- Building the priority queue takes O(n log n) time");
        System.out.println("- Generating codes takes O(n) time");
        
        System.out.println("\nWorst Case Time Complexity: O(n log n)");
        System.out.println("- Occurs when every character has the same frequency");
        System.out.println("- The priority queue operations still dominate at O(n log n)");
    }
}