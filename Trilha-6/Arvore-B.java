import java.util.*;

class BTreeNode {
    int[] keys;
    int t;
    BTreeNode[] children;
    int n;
    boolean leaf;

    public BTreeNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        this.keys = new int[2 * t - 1];
        this.children = new BTreeNode[2 * t];
        this.n = 0;
    }

    public void traverse() {
        for (int i = 0; i < n; i++) {
            if (!leaf) {
                children[i].traverse();
            }
            System.out.print(keys[i] + " ");
        }
        if (!leaf) {
            children[n].traverse();
        }
    }

    public BTreeNode search(int k) {
        int i = 0;
        while (i < n && k > keys[i]) {
            i++;
        }
        if (i < n && keys[i] == k) {
            return this;
        }
        if (leaf) {
            return null;
        }
        return children[i].search(k);
    }
}

class BTree {
    private BTreeNode root;
    private int t;

    public BTree(int t) {
        this.t = t;
        this.root = new BTreeNode(t, true);
    }

    public void traverse() {
        if (root != null) {
            root.traverse();
        }
        System.out.println();
    }

    public void insert(int k) {
        if (root.n == 2 * t - 1) {
            BTreeNode s = new BTreeNode(t, false);
            s.children[0] = root;
            splitChild(s, 0, root);
            root = s;
        }
        insertNonFull(root, k);
    }

    private void insertNonFull(BTreeNode x, int k) {
        int i = x.n - 1;
        if (x.leaf) {
            while (i >= 0 && k < x.keys[i]) {
                x.keys[i + 1] = x.keys[i];
                i--;
            }
            x.keys[i + 1] = k;
            x.n++;
        } else {
            while (i >= 0 && k < x.keys[i]) {
                i--;
            }
            i++;
            if (x.children[i].n == 2 * t - 1) {
                splitChild(x, i, x.children[i]);
                if (k > x.keys[i]) {
                    i++;
                }
            }
            insertNonFull(x.children[i], k);
        }
    }

    private void splitChild(BTreeNode x, int i, BTreeNode y) {
        BTreeNode z = new BTreeNode(t, y.leaf);
        z.n = t - 1;
        System.arraycopy(y.keys, t, z.keys, 0, t - 1);
        if (!y.leaf) {
            System.arraycopy(y.children, t, z.children, 0, t);
        }
        y.n = t - 1;
        System.arraycopy(x.children, i + 1, x.children, i + 2, x.n - i);
        x.children[i + 1] = z;
        System.arraycopy(x.keys, i, x.keys, i + 1, x.n - i);
        x.keys[i] = y.keys[t - 1];
        x.n++;
    }
}

public class BTreeExample {
    public static void main(String[] args) {
        BTree t = new BTree(3);
        int[] values = {10, 20, 5, 6, 12, 30, 7, 17};
        
        for (int v : values) {
            t.insert(v);
            System.out.println("Após inserir " + v + ":");
            t.traverse();
        }
    }
}
