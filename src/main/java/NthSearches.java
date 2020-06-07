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

    private void inOrder(Node node, Consumer<Integer> callback) {
        if (node == null) {
            return;
        }

        inOrder(node.left, callback);

        callback.accept(node.data);

        inOrder(node.right, callback);
    }

    public int nthSmallest(int n) {
        AtomicInteger counter = new AtomicInteger(n);
        AtomicInteger result = new AtomicInteger();
        nthSmallest(treeRoot, counter, result);
        return result.get();
    }

    public int nthLargest(int n) {
        AtomicInteger counter = new AtomicInteger(n);
        AtomicInteger result = new AtomicInteger();
        nthLargest(treeRoot, counter, result);
        return result.get();
    }

    private void nthLargest(Node node, AtomicInteger n, AtomicInteger result) {
        if (node == null) {
            return;
        }

        nthLargest(node.right, n, result);

        if (n.decrementAndGet() == 0) {
            result.set(node.data);
            return;
        }

        nthLargest(node.left, n, result);
    }

    private void nthSmallest(Node node, AtomicInteger n, AtomicInteger result) {
        if (node == null) {
            return;
        }

        nthSmallest(node.left, n, result);

        if (n.decrementAndGet() == 0) {
            result.set(node.data);
            return;
        }

        nthSmallest(node.right, n, result);
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

        final int N = 2;
        System.out.println(String.format("%d-th Largest: %d", N, tree.nthLargest(N)));
        System.out.println(String.format("%d-th Smallest: %d", N, tree.nthSmallest(N)));
    }
}
