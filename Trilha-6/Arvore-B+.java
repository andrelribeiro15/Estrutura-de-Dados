import java.util.*;

class BPlusTreeNode {
    int[] keys;
    int t;
    BPlusTreeNode[] children;
    int n;
    boolean leaf;
    BPlusTreeNode next;

    public BPlusTreeNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        this.keys = new int[2 * t - 1];
        this.children = new BPlusTreeNode[2 * t];
        this.n = 0;
        this.next = null;
    }
}

class BPlusTree {
    private BPlusTreeNode root;
    private int t;

    public BPlusTree(int t) {
        this.t = t;
        this.root = new BPlusTreeNode(t, true);
    }

    public void traverse() {
        traverse(root);
        System.out.println();
    }

    private void traverse(BPlusTreeNode node) {
        if (node == null) return;
        for (int i = 0; i < node.n; i++) {
            if (!node.leaf) {
                traverse(node.children[i]);
            }
            System.out.print(node.keys[i] + " ");
        }
        if (!node.leaf) {
            traverse(node.children[node.n]);
        }
    }

    public void insert(int key) {
        if (root.n == 2 * t - 1) {
            BPlusTreeNode newRoot = new BPlusTreeNode(t, false);
            newRoot.children[0] = root;
            splitChild(newRoot, 0, root);
            root = newRoot;
        }
        insertNonFull(root, key);
    }

    private void insertNonFull(BPlusTreeNode node, int key) {
        int i = node.n - 1;
        if (node.leaf) {
            while (i >= 0 && key < node.keys[i]) {
                node.keys[i + 1] = node.keys[i];
                i--;
            }
            node.keys[i + 1] = key;
            node.n++;
        } else {
            while (i >= 0 && key < node.keys[i]) {
                i--;
            }
            i++;
            if (node.children[i].n == 2 * t - 1) {
                splitChild(node, i, node.children[i]);
                if (key > node.keys[i]) {
                    i++;
                }
            }
            insertNonFull(node.children[i], key);
        }
    }

    private void splitChild(BPlusTreeNode parent, int index, BPlusTreeNode child) {
        BPlusTreeNode newChild = new BPlusTreeNode(t, child.leaf);
        newChild.n = t - 1;
        System.arraycopy(child.keys, t, newChild.keys, 0, t - 1);
        if (!child.leaf) {
            System.arraycopy(child.children, t, newChild.children, 0, t);
        }
        child.n = t - 1;
        System.arraycopy(parent.children, index + 1, parent.children, index + 2, parent.n - index);
        parent.children[index + 1] = newChild;
        System.arraycopy(parent.keys, index, parent.keys, index + 1, parent.n - index);
        parent.keys[index] = child.keys[t - 1];
        parent.n++;
        if (child.leaf) {
            newChild.next = child.next;
            child.next = newChild;
        }
    }
}

public class BPlusTreeExample {
    public static void main(String[] args) {
        BPlusTree tree = new BPlusTree(2);
        int[] values = {15, 5, 25, 10, 20, 30, 35};
        
        for (int value : values) {
            tree.insert(value);
            System.out.println("Após inserir " + value + ":");
            tree.traverse();
        }
    }
}
