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

    public boolean insert(int key) {
        validate_key(key);
        if (root == null) {
            root = new TreeNode(key);
            return true;
        }
    }

    public void delete(int key) {

    }


    // Returns the smallest node
    public int findMin() {
        return findMinHelper(root);
    }

    // Internal method to find the node with the smallest value using recursion
    public int findMinHelper(TreeNode node) {
        if (node == null) {
            return null;
        } else if (node.left == null) {
            return node;
        }
        return findMinHelper(node.left);
    }

    // Returns the biggest node
    public int findMax() {
        return findMaxHelper(root);
    }

    // Internal method to find biggest node using a while loop
    public int findMaxHelper(TreeNode node) {
        if (node != null) {
            while( node.right != null) {
                node = node.right;
            }
        } else {
            return null;
        }
        return node;
    }

    public boolean contains(int key) {
        validate_key(key)
        if ()
    }

    // Returns the height, the length of the longest path from root to a leaf node
    public int height() {
        return heightHelper(root);
    }

    // Internal method that recursively finds the longest path, finding the count
    public int heightHelper(TreeNode node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(heightHelper(node.left), heightHelper(node.right));
    }

    // Returns the total count of the nodes in the BST
    public int size() {
        return sizeHelper(root);
    }

    // Internal method that recursively checks and adds the child nodes to add to the total count
    public int sizeHelper(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + sizeHelper(node.left) + sizeHelper(node.right);
    }

    // Checks key range, throws error if it does
    public void validate_key(int key) {
        if (key > 99 || key < 1) {
            throw new IllegalArgumentException("Key out of range [1, 99]: " + key);
        }
    }
}