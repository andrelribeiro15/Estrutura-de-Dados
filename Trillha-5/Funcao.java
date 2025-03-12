import java.util.*;

class StringHashTest {
    private static final int TABLE_SIZE = 100;

    public static int hashString(String key) {
        int hash = 0;
        for (char c : key.toCharArray()) {
            hash = (hash * 31 + c) % TABLE_SIZE; // Função de hash eficiente
        }
        return hash;
    }

    public static void testHashFunction(String[] keys) {
        int[] table = new int[TABLE_SIZE];
        int collisions = 0;

        for (String key : keys) {
            int index = hashString(key);
            if (table[index] > 0) {
                collisions++;
            }
            table[index]++;
        }

        System.out.println("Total de chaves testadas: " + keys.length);
        System.out.println("Colisões observadas: " + collisions);
        System.out.println("Proporção de colisões: " + (double) collisions / keys.length);
    }

    public static void main(String[] args) {
        String[] sampleData1 = {"apple", "banana", "cherry", "date", "elderberry", "fig", "grape", "honeydew", "kiwi", "lemon"};
        String[] sampleData2 = new String[500];
        Random random = new Random();

        for (int i = 0; i < 500; i++) {
            sampleData2[i] = "word" + random.nextInt(10000);
        }

        System.out.println("Teste com pequeno conjunto de palavras:");
        testHashFunction(sampleData1);
        
        System.out.println("\nTeste com grande conjunto de palavras:");
        testHashFunction(sampleData2);
    }
}
