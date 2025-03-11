import java.util.Arrays;

class MaxHeap {
    private int[] heap;
    private int size;
    private int capacity;

    // Construtor para inicializar o Max-Heap com uma capacidade inicial
    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    // Função para obter o índice do pai
    private int parent(int i) {
        return (i - 1) / 2;
    }

    // Função para obter o índice do filho esquerdo
    private int leftChild(int i) {
        return 2 * i + 1;
    }

    // Função para obter o índice do filho direito
    private int rightChild(int i) {
        return 2 * i + 2;
    }

    // Função para trocar dois elementos no heap
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Função para garantir a propriedade de Max-Heap (Heapify)
    private void heapifyUp(int i) {
        while (i > 0 && heap[parent(i)] < heap[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    // Função para manter a propriedade de Max-Heap após a remoção
    private void heapifyDown(int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int largest = i;

        // Verifica se o filho esquerdo é maior que o nó atual
        if (left < size && heap[left] > heap[largest]) {
            largest = left;
        }

        // Verifica se o filho direito é maior que o nó atual ou o filho esquerdo
        if (right < size && heap[right] > heap[largest]) {
            largest = right;
        }

        // Se o maior elemento não for o nó atual, troca e continua o processo
        if (largest != i) {
            swap(i, largest);
            heapifyDown(largest);
        }
    }

    // Função para inserir um novo elemento no Max-Heap
    public void insert(int value) {
        if (size == capacity) {
            System.out.println("Heap overflow");
            return;
        }

        // Insere o novo valor na última posição
        heap[size] = value;
        size++;

        // Ajusta o heap para manter a propriedade do Max-Heap
        heapifyUp(size - 1);
    }

    // Função para remover o maior elemento (a raiz) do Max-Heap
    public int remove() {
        if (size == 0) {
            System.out.println("Heap underflow");
            return -1;
        }

        // O maior elemento é a raiz
        int root = heap[0];

        // Coloca o último elemento no topo
        heap[0] = heap[size - 1];
        size--;

        // Ajusta o heap para manter a propriedade do Max-Heap
        heapifyDown(0);

        return root;
    }

    // Função para exibir o conteúdo do heap (para fins de depuração)
    public void printHeap() {
        System.out.println(Arrays.toString(Arrays.copyOfRange(heap, 0, size)));
    }
}

// Classe que implementa uma Fila de Prioridades com Max-Heap
class PriorityQueue {
    private MaxHeap maxHeap;

    public PriorityQueue(int capacity) {
        maxHeap = new MaxHeap(capacity);
    }

    // Função para inserir um elemento na fila de prioridades
    public void insert(int value) {
        maxHeap.insert(value);
    }

    // Função para remover o maior valor da fila de prioridades
    public int removeMax() {
        return maxHeap.remove();
    }

    // Função para exibir o conteúdo da fila de prioridades
    public void printQueue() {
        maxHeap.printHeap();
    }
}

// Classe principal para testar a implementação
public class Main {
    public static void main(String[] args) {
        // Criar uma Fila de Prioridades com capacidade 10
        PriorityQueue pq = new PriorityQueue(10);

        // Inserir elementos na fila de prioridades
        pq.insert(10);
        pq.insert(20);
        pq.insert(5);
        pq.insert(30);
        pq.insert(25);

        // Exibir a fila de prioridades (Max-Heap)
        System.out.print("Fila de Prioridades: ");
        pq.printQueue(); // Exibe os elementos na ordem do Max-Heap

        // Remover o maior elemento (máximo) da fila de prioridades
        System.out.println("Elemento removido: " + pq.removeMax());

        // Exibir a fila de prioridades após remoção
        System.out.print("Fila de Prioridades após remoção: ");
        pq.printQueue();
    }
}
