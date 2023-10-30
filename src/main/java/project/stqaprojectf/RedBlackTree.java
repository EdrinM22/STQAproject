package project.stqaprojectf;

import javafx.scene.paint.Color;

class Node {
    int data;
    Node left, right, parent;
    Color color;

    public Node(int data) {
        this.data = data;
        this.color = Color.RED;
        left = right = parent = null;
    }
}


class RedBlackTree {
    public Node root;
    private static final Color BLACK = Color.BLACK;
    private static final Color RED = Color.RED;

    public void insert(int data) {
        Node node = new Node(data);
        root = insertNode(root, node);
        fixInsert(node);
    }
    public void clear() {
        root = null;
    }
    private Node insertNode(Node root, Node node) {
        if (root == null)
            return node;
        if (node.data < root.data) {
            root.left = insertNode(root.left, node);
            root.left.parent = root;
        } else if (node.data > root.data) {
            root.right = insertNode(root.right, node);
            root.right.parent = root;
        }
        return root;
    }

    private void fixInsert(Node node) {
        while (node != root && node.color != BLACK && node.parent.color == RED) {
            if (node.parent == node.parent.parent.left) {
                Node uncle = node.parent.parent.right;
                if (uncle != null && uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        node = node.parent;
                        rotateLeft(node);
                    }
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    rotateRight(node.parent.parent);
                }
            } else {
                Node uncle = node.parent.parent.left;
                if (uncle != null && uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rotateRight(node);
                    }
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    rotateLeft(node.parent.parent);
                }
            }
        }
        root.color = BLACK;
    }
    public void delete(int data) {
        Node nodeToDelete = findNode(root, data);
        if (nodeToDelete != null) {
            root = deleteNode(root, nodeToDelete);
        }
    }

    private Node deleteNode(Node root, Node node) {
        if (node == null)
            return root;

        if (node.left == null && node.right == null) {
            if (node.parent == null) {
                root = null;
            } else {
                if (node == node.parent.left) {
                    node.parent.left = null;
                } else {
                    node.parent.right = null;
                }
            }
        } else if (node.left == null || node.right == null) {
            Node child = (node.left != null) ? node.left : node.right;

            if (node.parent == null) {
                root = child;
                child.parent = null;
            } else {
                if (node == node.parent.left) {
                    node.parent.left = child;
                } else {
                    node.parent.right = child;
                }
                child.parent = node.parent;
            }
        } else {
            Node successor = getMinimumNode(node.right);
            node.data = successor.data;
            root = deleteNode(root, successor);
        }

        return root;
    }

    public Node findNode(Node root, int data) {
        if (root == null || root.data == data)
            return root;

        if (data < root.data)
            return findNode(root.left, data);

        return findNode(root.right, data);
    }

    private Node getMinimumNode(Node node) {
        if (node == null || node.left == null)
            return node;
        return getMinimumNode(node.left);
    }
    private void rotateLeft(Node node) {
        Node rightChild = node.right;
        node.right = rightChild.left;
        if (node.right != null)
            node.right.parent = node;
        rightChild.parent = node.parent;
        if (node.parent == null)
            root = rightChild;
        else if (node == node.parent.left)
            node.parent.left = rightChild;
        else
            node.parent.right = rightChild;
        rightChild.left = node;
        node.parent = rightChild;
    }

    private void rotateRight(Node node) {
        Node leftChild = node.left;
        node.left = leftChild.right;
        if (node.left != null)
            node.left.parent = node;
        leftChild.parent = node.parent;
        if (node.parent == null)
            root = leftChild;
        else if (node == node.parent.left)
            node.parent.left = leftChild;
        else
            node.parent.right = leftChild;
        leftChild.right = node;
        node.parent = leftChild;
    }

    public void traverseTree(Node root) {
        if (root != null) {
            traverseTree(root.left);
            System.out.print(root.data + " ");
            traverseTree(root.right);
        }
    }

    public Node getRoot() {
        return root;
    }
}

