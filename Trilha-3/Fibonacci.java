import java.util.HashMap;

class Fibonacci {
    // Abordagem recursiva simples
    public static long fibonacciRecursivo(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursivo(n - 1) + fibonacciRecursivo(n - 2);
    }

    // Abordagem com Memoization
    private static HashMap<Integer, Long> memo = new HashMap<>();
    public static long fibonacciMemoization(int n) {
        if (n <= 1) {
            return n;
        }
        if (!memo.containsKey(n)) {
            memo.put(n, fibonacciMemoization(n - 1) + fibonacciMemoization(n - 2));
        }
        return memo.get(n);
    }

    // Abordagem Iterativa
    public static long fibonacciIterativo(int n) {
        if (n <= 1) {
            return n;
        }
        long a = 0, b = 1, temp;
        for (int i = 2; i <= n; i++) {
            temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }

    public static void main(String[] args) {
        int n = 40; // Teste com um número maior para comparar desempenho
        
        System.out.println("Fibonacci Recursivo: " + fibonacciRecursivo(n));
        System.out.println("Fibonacci com Memoization: " + fibonacciMemoization(n));
        System.out.println("Fibonacci Iterativo: " + fibonacciIterativo(n));
    }
}

/*
Análise de Desempenho:
1. Recursão Simples: O(2^n) - Extremamente ineficiente para valores grandes, pois repete cálculos desnecessários.
2. Memoization: O(n) - Armazena valores já calculados, evitando recomputação e melhorando o desempenho.
3. Iterativo: O(n) - Melhor abordagem em termos de espaço e tempo, pois usa apenas variáveis auxiliares.
*/