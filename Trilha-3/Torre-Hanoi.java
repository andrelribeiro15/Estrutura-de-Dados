// Classe para resolver o problema da Torre de Hanói
public class TorreDeHanoi {

    // Função recursiva para resolver a Torre de Hanói
    public void moverDiscos(int n, char origem, char destino, char auxiliar) {
        // Caso base: Se há apenas um disco, mova-o diretamente para o destino
        if (n == 1) {
            System.out.println("Mover disco 1 de " + origem + " para " + destino);
            return;
        }

        // Passo 1: Mova os n-1 discos da origem para a auxiliar
        moverDiscos(n - 1, origem, auxiliar, destino);

        // Passo 2: Mova o disco maior (n) da origem para o destino
        System.out.println("Mover disco " + n + " de " + origem + " para " + destino);

        // Passo 3: Mova os n-1 discos da auxiliar para o destino
        moverDiscos(n - 1, auxiliar, destino, origem);
    }

    public static void main(String[] args) {
        TorreDeHanoi torre = new TorreDeHanoi();
        int n = 3; // Número de discos
        torre.moverDiscos(n, 'A', 'C', 'B'); // A, B, C são as hastes de origem, destino e auxiliar, respectivamente
    }
}