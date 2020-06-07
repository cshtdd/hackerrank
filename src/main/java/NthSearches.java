import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;

public class NthSearches {
    private static class Node {
        public int data;
        public Node left;
        public Node right;

        public Node(int data) {
            this(data, null, null);
        }

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private Node treeRoot = null;

    public void add(int n) {
        Node newNode = add(treeRoot, n);
        if (treeRoot == null) {
            treeRoot = newNode;
        }
    }

    private Node add(Node node, int n) {
        if (node == null) {
            return new Node(n);
        }

        if (n >= node.data) {
            Node newNode = add(node.right, n);
            if (node.right == null) {
                node.right = newNode;
            }
            return newNode;
        }

        Node newNode = add(node.left, n);
        if (node.left == null) {
            node.left = newNode;
        }
        return newNode;
    }

    public void print() {
        List<String> dataPieces = new ArrayList<>();
        inOrder(treeRoot, n -> dataPieces.add(String.format("%d", n)));
        String result = String.join(", ", dataPieces);
        System.out.println(result);
    }

    private void inOrder(Node node, Consumer<Integer> callback){
        if (node == null) {
            return;
        }

        inOrder(node.left, callback);

        callback.accept(node.data);

        inOrder(node.right, callback);
    }

    public int nthLargest(int n) {
        final int[] result = new int[2];
        result[0] = n;
        nthLargest(treeRoot, result);
        return result[1];
    }

    private void nthLargest(Node node, int[] counters){
        if (node == null){
            return;
        }

        nthLargest(node.right, counters);

        counters[0]--;
        if (counters[0] == 0){
            counters[1] = node.data;
            return;
        }

        nthLargest(node.left, counters);
    }

    public static void main(String[] args) {
        /*
                  5
                /   \
              3      9
             / \    /
            1   4  7
             \     \
              2     8
         */

        NthSearches tree = new NthSearches();
        tree.add(5);
        tree.add(3);
        tree.add(9);
        tree.add(7);
        tree.add(8);
        tree.add(1);
        tree.add(2);
        tree.add(4);

        tree.print();
        System.out.println(String.format("%d-th Largest: %d", 3, tree.nthLargest(3)));
    }
}
