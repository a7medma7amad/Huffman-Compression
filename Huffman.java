import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Huffman {
    private static HashMap<Character, String> coding;
    private static Node root;

    public static void main(String[] args) {
        String input = args[0];
        System.out.println("Input:");
        System.out.println(input);
        System.out.println("-----------------");
        String encoded = encode(input);
        System.out.println("Encoded:");
        System.out.println(encoded);
        System.out.println("-----------------");
        String decoded = decode(encoded);
        System.out.println("Decoded:");
        System.out.println(decoded);
        System.out.println("-----------------");


    }

    public static void printTree(Node root) {
        if (root.isLeaf()) {
            System.out.println(root.character + " " + root.frequency);
        } else {
            printTree(root.left);
            printTree(root.right);
        }
    }

    public static String encode(String input) {
        HashMap<Character,Integer> frequencies = getFrequencies(input);
        buildTree(frequencies);
        coding = new HashMap<>();
        buildEncodingTable(root, "");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            sb.append(coding.get(c));
        }
        return sb.toString();
    }
    public static String decode(String input) {
        StringBuilder sb = new StringBuilder();
        Node current = root;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '0') {
                current = current.left;
            } else {
                current = current.right;
            }
            if (current.isLeaf()) {
                sb.append(current.character);
                current = root;
            }
        }
        return sb.toString();
    }
    public static HashMap<Character,Integer> getFrequencies(String input) {
        HashMap<Character, Integer> frequencies = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (frequencies.containsKey(c)) {
                frequencies.put(c, frequencies.get(c) + 1);
            } else {
                frequencies.put(c, 1);
            }
        }
        return frequencies;
    }
    public static void buildTree(HashMap<Character,Integer> frequencies) {
        Queue<Node> queue = new PriorityQueue<>(new NodeComparator());
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            queue.add(node);

        }
        while (queue.size() > 1) {
            Node left = queue.poll();
            Node right = queue.poll();
            Node parent = new Node(left, right);
            queue.add(parent);
        }
        root = queue.poll();
    }
    public static void buildEncodingTable(Node root, String prefix) {
        if (root.isLeaf()) {
            coding.put(root.character, prefix);
        } else {
            buildEncodingTable(root.left, prefix + "0");
            buildEncodingTable(root.right, prefix + "1");
        }
    }


}
