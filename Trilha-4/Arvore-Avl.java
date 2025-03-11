// Classe que representa um nó da árvore AVL
class Node {
    int data;
    Node left, right;
    int height;

    // Construtor para criar um nó com valor inicial
    Node(int data) {
        this.data = data;
        this.left = this.right = null;
        this.height = 1; // A altura de um nó folha é 1
    }
}

// Classe que implementa a Árvore AVL
class AVLTree {
    private Node root;

    // Função para obter a altura de um nó
    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    // Função para obter o fator de balanceamento de um nó
    private int balanceFactor(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    // Função para rotacionar à direita (Right Rotation)
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Realiza a rotação
        x.right = y;
        y.left = T2;

        // Atualiza as alturas
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x; // Retorna a nova raiz
    }

    // Função para rotacionar à esquerda (Left Rotation)
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Realiza a rotação
        y.left = x;
        x.right = T2;

        // Atualiza as alturas
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y; // Retorna a nova raiz
    }

    // Função para balancear a árvore após a inserção ou remoção
    private Node balance(Node node) {
        int balance = balanceFactor(node);

        // Se o nó estiver desbalanceado, temos 4 casos:

        // Caso 1: Rotação simples à direita (Left-Left Case)
        if (balance > 1 && node.data > node.left.data) {
            return rightRotate(node);
        }

        // Caso 2: Rotação simples à esquerda (Right-Right Case)
        if (balance < -1 && node.data < node.right.data) {
            return leftRotate(node);
        }

        // Caso 3: Rotação dupla à direita (Left-Right Case)
        if (balance > 1 && node.data < node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Caso 4: Rotação dupla à esquerda (Right-Left Case)
        if (balance < -1 && node.data > node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node; // Retorna o nó balanceado
    }

    // Função para inserir um valor na árvore
    public void insert(int data) {
        root = insert(root, data);
    }

    private Node insert(Node node, int data) {
        // Caso base: se o nó for null, cria um novo nó
        if (node == null) {
            return new Node(data);
        }

        // Realiza a inserção de forma recursiva
        if (data < node.data) {
            node.left = insert(node.left, data);
        } else if (data > node.data) {
            node.right = insert(node.right, data);
        } else {
            return node; // Valores duplicados não são permitidos
        }

        // Atualiza a altura do nó
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        // Balanceia a árvore
        return balance(node);
    }

    // Função para buscar o nó com o valor mínimo
    private Node minNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Função para remover um valor da árvore
    public void delete(int data) {
        root = delete(root, data);
    }

    private Node delete(Node node, int data) {
        if (node == null) {
            return node; // Caso base: se o nó for null, não há nada a fazer
        }

        // Realiza a remoção recursiva
        if (data < node.data) {
            node.left = delete(node.left, data);
        } else if (data > node.data) {
            node.right = delete(node.right, data);
        } else {
            // Nó a ser removido encontrado

            // Caso 1: Nó com apenas um filho ou nenhum filho
            if (node.left == null || node.right == null) {
                Node temp = node.left != null ? node.left : node.right;
                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                // Caso 2: Nó com dois filhos - encontra o sucessor in-order (menor valor na subárvore direita)
                Node temp = minNode(node.right);
                node.data = temp.data;
                node.right = delete(node.right, temp.data);
            }
        }

        // Se a árvore tiver apenas um nó, retorna o nó
        if (node == null) {
            return node;
        }

        // Atualiza a altura do nó
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        // Balanceia a árvore após a remoção
        return balance(node);
    }

    // Função para realizar o percurso em-order
    public void inOrder() {
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }

    // Função para obter a raiz da árvore
    public Node getRoot() {
        return root;
    }
}

// Classe principal para testar a Árvore AVL
public class Main {
    public static void main(String[] args) {
        AVLTree avl = new AVLTree();

        // Inserir elementos na árvore
        avl.insert(30);
        avl.insert(20);
        avl.insert(40);
        avl.insert(10);
        avl.insert(25);
        avl.insert(35);
        avl.insert(50);

        // Exibindo a árvore em ordem
        System.out.print("In-order após inserções: ");
        avl.inOrder(); // 10 20 25 30 35 40 50

        // Remover um elemento da árvore
        avl.delete(20);
        System.out.print("In-order após remoção de 20: ");
        avl.inOrder(); // 10 25 30 35 40 50

        // Remover outro elemento
        avl.delete(40);
        System.out.print("In-order após remoção de 40: ");
        avl.inOrder(); // 10 25 30 35 50
    }
}
