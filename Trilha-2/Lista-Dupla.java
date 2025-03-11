class DoublyNode {
    int data;
    DoublyNode next;
    DoublyNode prev;

    public DoublyNode(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

class DoublyLinkedList {
    private DoublyNode head;
    private DoublyNode tail;

    public void inserirNoInicio(int data) {
        DoublyNode novoNo = new DoublyNode(data);
        if (head == null) {
            head = tail = novoNo;
        } else {
            novoNo.next = head;
            head.prev = novoNo;
            head = novoNo;
        }
    }

    public boolean removerDoFinal() {
        if (tail == null) {
            return false;
        }
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        return true;
    }

    public void imprimirListaDireta() {
        DoublyNode temp = head;
        while (temp != null) {
            System.out.print(temp.data + " <-> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public void imprimirListaReversa() {
        DoublyNode temp = tail;
        while (temp != null) {
            System.out.print(temp.data + " <-> ");
            temp = temp.prev;
        }
        System.out.println("null");
    }
}

public class ListaDuplamenteEncadeada {
    public static void main(String[] args) {
        DoublyLinkedList lista = new DoublyLinkedList();
        
        lista.inserirNoInicio(10);
        lista.inserirNoInicio(20);
        lista.inserirNoInicio(30);
        
        System.out.println("Lista em ordem direta:");
        lista.imprimirListaDireta();
        
        System.out.println("Lista em ordem reversa:");
        lista.imprimirListaReversa();
        
        lista.removerDoFinal();
        System.out.println("Lista após remoção do final:");
        lista.imprimirListaDireta();
    }
}
