class Node {
    int data;
    Node left, right;

    // Construtor para criar um novo nó
    Node(int data) {
        this.data = data;
        this.left = this.right = null;
    }
}

// Classe que implementa a Árvore Binária de Busca (BST)
class BinarySearchTree {
    Node root;

    // Função para inserir um novo valor na árvore
    public void insert(int data) {
        root = insertRecursive(root, data);
    }

    private Node insertRecursive(Node node, int data) {
        // Caso base: se o nó for null, cria um novo nó
        if (node == null) {
            return new Node(data);
        }

        // Se o valor for menor que o nó atual, vai para a subárvore esquerda
        if (data < node.data) {
            node.left = insertRecursive(node.left, data);
        }
        // Se o valor for maior que o nó atual, vai para a subárvore direita
        else if (data > node.data) {
            node.right = insertRecursive(node.right, data);
        }

        return node;
    }

    // Função para buscar um valor na árvore
    public boolean search(int data) {
        return searchRecursive(root, data);
    }

    private boolean searchRecursive(Node node, int data) {
        // Caso base: se o nó for null, significa que o valor não foi encontrado
        if (node == null) {
            return false;
        }

        // Se o valor for igual ao valor do nó, o valor foi encontrado
        if (data == node.data) {
            return true;
        }

        // Se o valor for menor que o valor do nó, busca na subárvore esquerda
        if (data < node.data) {
            return searchRecursive(node.left, data);
        }

        // Se o valor for maior que o valor do nó, busca na subárvore direita
        return searchRecursive(node.right, data);
    }

    // Função para remover um valor da árvore
    public void delete(int data) {
        root = deleteRecursive(root, data);
    }

    private Node deleteRecursive(Node node, int data) {
        if (node == null) {
            return null;
        }

        // Se o valor a ser deletado for menor que o valor do nó, vai para a subárvore esquerda
        if (data < node.data) {
            node.left = deleteRecursive(node.left, data);
        }
        // Se o valor a ser deletado for maior que o valor do nó, vai para a subárvore direita
        else if (data > node.data) {
            node.right = deleteRecursive(node.right, data);
        }
        // Se o valor a ser deletado for igual ao valor do nó, encontramos o nó a ser removido
        else {
            // Caso 1: Nó com apenas um filho ou nenhum filho
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            // Caso 2: Nó com dois filhos - encontra o valor mínimo na subárvore direita
            node.data = findMin(node.right).data;

            // Deleta o valor mínimo na subárvore direita
            node.right = deleteRecursive(node.right, node.data);
        }
        return node;
    }

    // Função para encontrar o nó com o valor mínimo (menor valor) na subárvore
    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Funções para percorrer a árvore (In-order, Pre-order, Post-order)
    public void inOrder() {
        inOrderRecursive(root);
        System.out.println();
    }

    private void inOrderRecursive(Node node) {
        if (node != null) {
            inOrderRecursive(node.left);
            System.out.print(node.data + " ");
            inOrderRecursive(node.right);
        }
    }

    public void preOrder() {
        preOrderRecursive(root);
        System.out.println();
    }

    private void preOrderRecursive(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrderRecursive(node.left);
            preOrderRecursive(node.right);
        }
    }

    public void postOrder() {
        postOrderRecursive(root);
        System.out.println();
    }

    private void postOrderRecursive(Node node) {
        if (node != null) {
            postOrderRecursive(node.left);
            postOrderRecursive(node.right);
            System.out.print(node.data + " ");
        }
    }
}

// Classe principal
public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Inserir elementos na árvore
        bst.insert(50);
        bst.insert(30);
        bst.insert(20);
        bst.insert(40);
        bst.insert(70);
        bst.insert(60);
        bst.insert(80);

        // Exibindo os percursos
        System.out.print("In-order: ");
        bst.inOrder(); // 20 30 40 50 60 70 80

        System.out.print("Pre-order: ");
        bst.preOrder(); // 50 30 20 40 70 60 80

        System.out.print("Post-order: ");
        bst.postOrder(); // 20 40 30 60 80 70 50

        // Buscando elementos na árvore
        System.out.println("Buscar 40: " + bst.search(40)); // true
        System.out.println("Buscar 25: " + bst.search(25)); // false

        // Remover elementos da árvore
        bst.delete(20); // Remove 20
        bst.delete(30); // Remove 30

        System.out.print("In-order após remoções: ");
        bst.inOrder(); // 40 50 60 70 80
    }
}