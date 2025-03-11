class Recursao {
    public static long fatorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * fatorial(n - 1);
    }

    public static void main(String[] args) {
        int numero = 5;
        System.out.println("Fatorial de " + numero + " é: " + fatorial(numero));
    }
} 

/*
Complexidade de Tempo:
O algoritmo possui complexidade O(n), pois a função chama a si mesma n vezes até atingir o caso base.
*/
