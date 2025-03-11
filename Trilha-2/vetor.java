import java.util.ArrayList;
import java.util.List;

public class VetorOperacoes {
    public static String buscarNumero(List<Integer> vetor, int numero) {
        int index = vetor.indexOf(numero);
        if (index != -1) {
            return "Número " + numero + " encontrado na posição " + index;
        }
        return "Número não encontrado no vetor";
    }

    public static String removerElemento(List<Integer> vetor, int posicao) {
        if (posicao >= 0 && posicao < vetor.size()) {
            int removido = vetor.remove(posicao);
            return "Elemento " + removido + " removido. Vetor atualizado: " + vetor;
        }
        return "Posição inválida";
    }

    public static void main(String[] args) {
        List<Integer> vetor = new ArrayList<>();
        vetor.add(10);
        vetor.add(20);
        vetor.add(30);
        vetor.add(40);
        vetor.add(50);
        vetor.add(60);
        vetor.add(70);
        vetor.add(80);
        vetor.add(90);
        vetor.add(100);

        // Testando a busca de um número
        int numeroProcurado = 30;
        System.out.println(buscarNumero(vetor, numeroProcurado));

        // Testando a remoção de um elemento
        int posicaoParaRemover = 4;
        System.out.println(removerElemento(vetor, posicaoParaRemover));
    }
}

