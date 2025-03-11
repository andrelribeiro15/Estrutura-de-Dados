class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    private Node head;

    public void inserirNoInicio(int data) {
        Node novoNo = new Node(data);
        novoNo.next = head;
        head = novoNo;
    }

    public void inserirNoFinal(int data) {
        Node novoNo = new Node(data);
        if (head == null) {
            head = novoNo;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = novoNo;
    }

    public boolean removerNaPosicao(int posicao) {
        if (head == null || posicao < 0) {
            return false;
        }
        if (posicao == 0) {
            head = head.next;
            return true;
        }
        Node temp = head;
        for (int i = 0; temp != null && i < posicao - 1; i++) {
            temp = temp.next;
        }
        if (temp == null || temp.next == null) {
            return false;
        }
        temp.next = temp.next.next;
        return true;
    }

    public String buscarElemento(int valor) {
        Node temp = head;
        int posicao = 0;
        while (temp != null) {
            if (temp.data == valor) {
                return "Valor " + valor + " encontrado na posição " + posicao;
            }
            temp = temp.next;
            posicao++;
        }
        return "Valor não encontrado na lista";
    }

    public void imprimirLista() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
}

public class ListaEncadeada {
    public static void main(String[] args) {
        LinkedList lista = new LinkedList();
        
        lista.inserirNoInicio(10);
        lista.inserirNoInicio(20);
        lista.inserirNoFinal(30);
        lista.inserirNoFinal(40);
        
        System.out.println("Lista após inserções:");
        lista.imprimirLista();
        
        System.out.println(lista.buscarElemento(30));
        System.out.println(lista.buscarElemento(50));
        
        lista.removerNaPosicao(1);
        System.out.println("Lista após remoção na posição 1:");
        lista.imprimirLista();
    }
}
