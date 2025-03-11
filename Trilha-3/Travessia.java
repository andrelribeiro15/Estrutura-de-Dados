class Node {
    int valor;
    Node esquerda, direita;

    public Node(int valor) {
        this.valor = valor;
        this.esquerda = this.direita = null;
    }
}

class ArvoreBinaria {
    Node raiz;

    public void inOrder(Node node) {
        if (node != null) {
            inOrder(node.esquerda);
            System.out.print(node.valor + " ");
            inOrder(node.direita);
        }
    }

    public void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.valor + " ");
            preOrder(node.esquerda);
            preOrder(node.direita);
        }
    }

    public void postOrder(Node node) {
        if (node != null) {
            postOrder(node.esquerda);
            postOrder(node.direita);
            System.out.print(node.valor + " ");
        }
    }
}

public class PercursoArvore {
    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();
        arvore.raiz = new Node(1);
        arvore.raiz.esquerda = new Node(2);
        arvore.raiz.direita = new Node(3);
        arvore.raiz.esquerda.esquerda = new Node(4);
        arvore.raiz.esquerda.direita = new Node(5);
        
        System.out.println("Percurso In-Order:");
        arvore.inOrder(arvore.raiz);
        
        System.out.println("\nPercurso Pre-Order:");
        arvore.preOrder(arvore.raiz);
        
        System.out.println("\nPercurso Post-Order:");
        arvore.postOrder(arvore.raiz);
    }
}
