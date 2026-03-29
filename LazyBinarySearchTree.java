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
        return insertHelper(key, root);
    }

    private boolean insertHelper(int key, TreeNode node) {
        if (key == node.key) { // Checks if duplicate node is marked deleted. If it is, mark deleted false
            if (node.deleted) {
                node.deleted = false;
                return true;
            }
            return false; // If duplicate is present in the tree, then insert failed
        } else if (key < node.key) { // Checks left subtree
            if (node.left == null) {
                node.left = new TreeNode(key);
                return true;
            }
            return insertHelper(key, node.left);
        } else { // Checks right subtree
            if (node.right == null) {
                node.right = new TreeNode(key);
                return true;
            }
            return insertHelper(key, node.right);
        }
    }

    // Uses lazy deletion and marks a node deleted = true
    public boolean delete(int key) {
        validate_key(key); // Validates key range
        TreeNode target = findNode(root, key); // Find the target node
        if (target != null && !target.deleted) { // If node is NOT marked deleted, set it to deleted property
            target.deleted = true;
            return true;
        }
        return false;
    }

    // Internal method that helps find a node with the input key, and returns that node
    private TreeNode findNode(TreeNode node, int key) {
        if (node == null || node.key == key) {
            return node;
        }
        if (key < node.key) {
            return findNode(node.left, key);
        }
        return findNode(node.right, key);
    }

    // Returns the smallest node
    public int findMin() {
        return findMinHelper(root);
    }

    // Internal method to find the node with the smallest value using recursion
    private int findMinHelper(TreeNode node) {
        if (node == null) {
            return -1;
        }

        int checkLeft = findMinHelper(node.left);
        if (checkLeft != -1) {
            return checkLeft;
        }

        if (!node.deleted) {
            return node.key;
        }

        return findMinHelper(node.right);
    }

    // Returns the biggest node
    public int findMax() {
        return findMaxHelper(root);
    }

    // Internal method to find biggest node using a while loop
    private int findMaxHelper(TreeNode node) {
        if (node == null) {
            return -1;
        }

        int checkRight = findMaxHelper(node.right);
        if (checkRight != -1) {
            return checkRight;
        }

        if (!node.deleted) {
            return node.key;
        }

        return findMaxHelper(node.left);
    }

    // Checks if BST contains input node
    public boolean contains(int key) {
        validate_key(key);
        return containsHelper(key, root);
    }

    // Internal method that recursively checks if a node in the BST has the same key as the input key
    private boolean containsHelper(int key, TreeNode node) {
        if (node == null) {
            return false;
        }

        if (key < node.key) {
            return containsHelper(key, node.left);
        } else if (key > node.key) {
            return containsHelper(key, node.right);
        } else {
            return !node.deleted;
        }
    }

    // Returns the height, the length of the longest path from root to a leaf node
    public int height() {
        return heightHelper(root);
    }

    // Internal method that recursively finds the longest path, finding the count
    private int heightHelper(TreeNode node) {
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
    private int sizeHelper(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + sizeHelper(node.left) + sizeHelper(node.right);
    }

    //
    @Override
    public String toString() {
        return preOrderTraversal(root).trim();
    }

    private String preOrderTraversal(TreeNode node) {
        if (node == null) {
            return "";
        }

        // Start at root
        String result = (node.deleted ? "*" : "") + node.key + " ";

        // Then traverse through children, left first then right
        result += preOrderTraversal(node.left);
        result += preOrderTraversal(node.right);

        return result;

    }
    // Checks key range, throws error if it does
    private void validate_key(int key) {
        if (key > 99 || key < 1) {
            throw new IllegalArgumentException("Key out of range [1, 99]: " + key);
        }
    }
}