public class Node {
    public Node left;
    public Node right;
    public char character;
    public int frequency;

    public Node(Node left, Node right) {
        this.left = left;
        this.right = right;
        this.character = '\0';
        this.frequency = left.frequency + right.frequency;
    }

    public Node(char character, int frequency) {
        this.left = null;
        this.right = null;
        this.character = character;
        this.frequency = frequency;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public String toString() {
        return "Node(" + character + ", " + frequency + ")";
    }
}
