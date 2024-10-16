// Java program to insert, delete, and search in an AVL tree
class AVLTree {

    // Node class representing a node in the AVL tree
    class Node {
        int key, height;
        Node left, right;

        Node(int d) {
            key = d;
            height = 1;
        }
    }

    Node root;

    // A utility function to get the height of the tree
    int height(Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    // A utility function to get the maximum of two integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // A right rotate utility function
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // A left rotate utility function
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get balance factor of node N
    int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    // Method to insert a key into the AVL tree
    Node insert(Node node, int key) {
        // Perform the normal BST insertion
        if (node == null)
            return (new Node(key));

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else // Duplicate keys are not allowed in AVL tree
            return node;

        // Update height of this ancestor node
        node.height = 1 + max(height(node.left), height(node.right));

        // Get the balance factor of this ancestor node
        int balance = getBalance(node);

        // If the node becomes unbalanced, then balance it

        // Left Left Case
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // A utility function to find the node with the minimum value
    Node minValueNode(Node node) {
        Node current = node;

        // Find the leftmost leaf
        while (current.left != null)
            current = current.left;

        return current;
    }

    // Method to delete a node from the AVL tree
    Node deleteNode(Node root, int key) {
        // Perform the normal BST deletion
        if (root == null)
            return root;

        if (key < root.key)
            root.left = deleteNode(root.left, key);
        else if (key > root.key)
            root.right = deleteNode(root.right, key);
        else {
            // Node with only one child or no child
            if ((root.left == null) || (root.right == null)) {
                Node temp = (root.left != null) ? root.left : root.right;

                if (temp == null) {
                    root = null;
                } else {
                    root = temp; // Copy the non-empty child
                }
            } else {
                // Node with two children: get the inorder successor
                Node temp = minValueNode(root.right);

                // Copy the inorder successor's data to this node
                root.key = temp.key;

                // Delete the inorder successor
                root.right = deleteNode(root.right, temp.key);
            }
        }

        if (root == null)
            return root;

        root.height = max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    // Method to search for a key in the AVL tree
    boolean search(Node root, int key) {
        if (root == null) {
            return false; // The key is not found
        }

        if (key < root.key) {
            return search(root.left, key); // Search in the left subtree
        } else if (key > root.key) {
            return search(root.right, key); // Search in the right subtree
        } else {
            return true; // The key is found
        }
    }

    // Utility function to print the inorder traversal of the tree
    void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + " ");
            inorder(node.right);
        }
    }

    // Main method to test the program
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // Insert nodes
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 40);
        tree.root = tree.insert(tree.root, 50);
        tree.root = tree.insert(tree.root, 25);

        // Print the inorder traversal of the AVL tree
        System.out.println("Inorder traversal of the AVL tree:");
        tree.inorder(tree.root);
        System.out.println();

        // Search for a key
        int keyToSearch = 30;
        if (tree.search(tree.root, keyToSearch)) {
            System.out.println("Key " + keyToSearch + " found in the AVL tree.");
        } else {
            System.out.println("Key " + keyToSearch + " not found in the AVL tree.");
        }

        // Delete a node
        tree.root = tree.deleteNode(tree.root, 30);
        System.out.println("Inorder traversal after deletion of 30:");
        tree.inorder(tree.root);
    }
}