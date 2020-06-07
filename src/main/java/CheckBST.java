public class CheckBST {
    private static class Node {
        public int data;
        public Node left;
        public Node right;
    }

    public static boolean checkBST(Node root) {
        if (root == null) {
            return true;
        }

        if (root.left != null && root.left.data >= root.data) {
            return false;
        }

        if (root.right != null && root.right.data <= root.data) {
            return false;
        }

        return checkBST(root.left) && checkBST(root.right);
    }
}
