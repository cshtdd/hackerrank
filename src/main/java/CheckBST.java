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

        if (root.left != null) {
            if (root.left.data >= root.data) {
                return false;
            }

            if (!checkBST(root.left)) {
                return false;
            }
        }

        if (root.right != null) {
            if (root.right.data <= root.data) {
                return false;
            }

            if (!checkBST(root.right)) {
                return false;
            }
        }

        return true;
    }
}
