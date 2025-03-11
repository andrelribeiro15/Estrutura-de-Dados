// Classe Node que representa um nó na lista encadeada
class Node {
    int data;
    Node next;

    // Construtor
    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

// Classe LinkedList que representa a lista encadeada
class LinkedList {
    Node head;

    // Método para adicionar um nó no início da lista
    public void add(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    // Função recursiva que conta o número de nós na lista
    public int countNodes(Node node) {
        if (node == null) {
            return 0; // Caso base: se o nó for null, retornamos 0
        }
        return 1 + countNodes(node.next); // 1 para o nó atual + contagem recursiva dos próximos nós
    }

    // Método para iniciar a contagem a partir da cabeça da lista
    public int countNodes() {
        return countNodes(head);
    }
}

public class Main {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        // Adicionando elementos à lista
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        // Contando o número de nós na lista
        int count = list.countNodes();
        System.out.println("Número de nós na lista: " + count);
    }
}