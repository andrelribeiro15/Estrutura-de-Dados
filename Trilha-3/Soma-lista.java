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

    // Função recursiva que soma os elementos da lista
    public int sum(Node node) {
        if (node == null) {
            return 0;  // Caso base: lista vazia
        }
        return node.data + sum(node.next);  // Soma o valor do nó atual com a soma do próximo nó
    }

    // Método para iniciar a soma a partir da cabeça da lista
    public int sum() {
        return sum(head);
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

        // Calculando a soma dos elementos da lista
        int result = list.sum();
        System.out.println("Soma dos elementos da lista: " + result);
    }
}