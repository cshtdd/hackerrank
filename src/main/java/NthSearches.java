public class NthSearches {
    private static class Node {
        public int data;
        public Node left;
        public Node right;

        public Node(int data){
            this(data, null, null);
        }

        public Node(int data, Node left, Node right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private Node root = null;

    public int nthLargest(int n){
        return -1;
    }

    public void add(int n){
        // TODO
    }

    public void traverse(){
        // TODO
    }

    public static void main(String[] args){
        NthSearches tree = new NthSearches();
        tree.add(5);
        tree.add(3);
        tree.add(9);
        tree.add(7);
        tree.add(8);
        tree.add(1);
        tree.add(2);
        tree.add(4);
        /*
                  5
                /   \
              3      9
             / \    /
            1   4  7
             \     \
              2     8
         */

        tree.traverse();
    }
}
