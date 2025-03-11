// Classe para representar um nó da árvore binária de busca
class Node {
    int data;
    Node left, right;

    // Construtor
    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

// Classe para representar a árvore binária de busca
class BinarySearchTree {
    Node root;

    // Método para inserir um valor na árvore binária de busca
    public void insert(int data) {
        root = insertRecursive(root, data);
    }

    private Node insertRecursive(Node node, int data) {
        // Caso base: se o nó for null, cria um novo nó
        if (node == null) {
            return new Node(data);
        }

        // Se o valor a ser inserido for menor que o valor do nó, vai para a subárvore esquerda
        if (data < node.data) {
            node.left = insertRecursive(node.left, data);
        }
        // Se o valor a ser inserido for maior que o valor do nó, vai para a subárvore direita
        else if (data > node.data) {
            node.right = insertRecursive(node.right, data);
        }

        return node;
    }

    // Função recursiva para buscar um valor na árvore
    public boolean search(int data) {
        return searchRecursive(root, data);
    }

    private boolean searchRecursive(Node node, int data) {
        // Caso base: se o nó for null, significa que o valor não foi encontrado
        if (node == null) {
            return false;
        }

        // Se o valor a ser buscado for igual ao valor do nó, encontrou
        if (data == node.data) {
            return true;
        }

        // Se o valor a ser buscado for menor que o valor do nó, busca na subárvore esquerda
        if (data < node.data) {
            return searchRecursive(node.left, data);
        }

        // Se o valor a ser buscado for maior que o valor do nó, busca na subárvore direita
        return searchRecursive(node.right, data);
    }
}

public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Inserindo elementos na árvore
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        // Buscando elementos na árvore
        System.out.println("Buscar 40: " + bst.search(40)); // true
        System.out.println("Buscar 25: " + bst.search(25)); // false
    }
}
