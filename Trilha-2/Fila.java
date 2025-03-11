import java.util.LinkedList;
import java.util.Queue;

class Fila {
    private int capacidade;
    private int[] fila;
    private int frente, tras, tamanho;

    public Fila(int capacidade) {
        this.capacidade = capacidade;
        this.fila = new int[capacidade];
        this.frente = 0;
        this.tras = -1;
        this.tamanho = 0;
    }

    public boolean enqueue(int elemento) {
        if (tamanho == capacidade) {
            System.out.println("Fila cheia!");
            return false;
        }
        tras = (tras + 1) % capacidade;
        fila[tras] = elemento;
        tamanho++;
        return true;
    }

    public Integer dequeue() {
        if (tamanho == 0) {
            System.out.println("Fila vazia!");
            return null;
        }
        int elemento = fila[frente];
        frente = (frente + 1) % capacidade;
        tamanho--;
        return elemento;
    }

    public void imprimirFila() {
        if (tamanho == 0) {
            System.out.println("Fila vazia!");
            return;
        }
        System.out.print("Fila: ");
        for (int i = 0; i < tamanho; i++) {
            System.out.print(fila[(frente + i) % capacidade] + " ");
        }
        System.out.println();
    }
}

class AtendimentoBanco {
    public static void simularAtendimento() {
        Queue<String> filaBanco = new LinkedList<>();
        filaBanco.add("Cliente 1");
        filaBanco.add("Cliente 2");
        filaBanco.add("Cliente 3");

        System.out.println("Iniciando atendimento no banco...");
        while (!filaBanco.isEmpty()) {
            System.out.println("Atendendo: " + filaBanco.poll());
        }
        System.out.println("Todos os clientes foram atendidos.");
    }
}

public class TesteFila {
    public static void main(String[] args) {
        Fila fila = new Fila(5);
        fila.enqueue(10);
        fila.enqueue(20);
        fila.enqueue(30);
        fila.imprimirFila();
        fila.dequeue();
        fila.imprimirFila();

        System.out.println("\nSimulação do atendimento bancário:");
        AtendimentoBanco.simularAtendimento();
    }
}
