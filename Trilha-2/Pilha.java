import java.util.Stack;

class Pilha {
    private int capacidade;
    private Stack<Integer> stack;

    public Pilha(int capacidade) {
        this.capacidade = capacidade;
        this.stack = new Stack<>();
    }

    public void push(int elemento) {
        if (stack.size() < capacidade) {
            stack.push(elemento);
        } else {
            System.out.println("Pilha cheia!");
        }
    }

    public Integer pop() {
        if (!stack.isEmpty()) {
            return stack.pop();
        } else {
            System.out.println("Pilha vazia!");
            return null;
        }
    }

    public boolean isVazia() {
        return stack.isEmpty();
    }

    public boolean isCheia() {
        return stack.size() == capacidade;
    }
}

class BalanceamentoParenteses {
    public static boolean verificarBalanceamento(String expressao) {
        Stack<Character> pilha = new Stack<>();
        for (char ch : expressao.toCharArray()) {
            if (ch == '(') {
                pilha.push(ch);
            } else if (ch == ')') {
                if (pilha.isEmpty()) {
                    return false;
                }
                pilha.pop();
            }
        }
        return pilha.isEmpty();
    }
}

public class TestePilha {
    public static void main(String[] args) {
        Pilha pilha = new Pilha(5);
        pilha.push(10);
        pilha.push(20);
        pilha.push(30);
        System.out.println("Pilha cheia? " + pilha.isCheia());
        System.out.println("Pilha vazia? " + pilha.isVazia());

        System.out.println("Removendo elemento: " + pilha.pop());
        
        String expressao = "((1+2) * (3/4))";
        System.out.println("Expressão balanceada? " + BalanceamentoParenteses.verificarBalanceamento(expressao));
    }
}
