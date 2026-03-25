public class LazyBinarySearchTree {

    // Private TreeNode class, creates the individual node in the BST
    private class TreeNode {
        int key;
        boolean deleted;
        TreeNode left, right;

        public TreeNode(int key) {
            this.key = key;
            this.deleted = false;
            this.left = null;
            this.right = null;
        }
    }

    private TreeNode root;

    public LazyBinarySearchTree() {
        this.root = null;
    }


}