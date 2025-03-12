import java.util.*;

class HashTableEfficient {
    private LinkedList<Entry>[] table;
    private int size;

    static class Entry {
        int key;
        String value;

        Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public HashTableEfficient(int capacity) {
        this.size = capacity;
        table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(int key) {
        return (key * 31) % size; // Função de hash eficiente usando número primo
    }

    public void insert(int key, String value) {
        int index = hash(key);
        for (Entry entry : table[index]) {
            if (entry.key == key) {
                entry.value = value;
                return;
            }
        }
        table[index].add(new Entry(key, value));
    }

    public String search(int key) {
        int index = hash(key);
        for (Entry entry : table[index]) {
            if (entry.key == key) {
                return entry.value;
            }
        }
        return null;
    }

    public void remove(int key) {
        int index = hash(key);
        table[index].removeIf(entry -> entry.key == key);
    }

    public static void testPerformance(int tableSize, int numElements) {
        HashTableEfficient hashTable = new HashTableEfficient(tableSize);
        Random random = new Random();
        int[] keys = new int[numElements];
        
        // Inserção
        for (int i = 0; i < numElements; i++) {
            keys[i] = random.nextInt(10000);
            hashTable.insert(keys[i], "Value" + i);
        }
        
        // Medir tempo de busca
        long startSearch = System.nanoTime();
        for (int key : keys) {
            hashTable.search(key);
        }
        long endSearch = System.nanoTime();
        double avgSearchTime = (endSearch - startSearch) / (double) numElements;
        
        // Medir tempo de remoção
        long startRemove = System.nanoTime();
        for (int key : keys) {
            hashTable.remove(key);
        }
        long endRemove = System.nanoTime();
        double avgRemoveTime = (endRemove - startRemove) / (double) numElements;
        
        System.out.println("Tamanho da tabela: " + tableSize);
        System.out.println("Tempo médio de busca: " + avgSearchTime + " ns");
        System.out.println("Tempo médio de remoção: " + avgRemoveTime + " ns");
        System.out.println("------------------------------");
    }

    public static void main(String[] args) {
        testPerformance(50, 500);
        testPerformance(100, 500);
        testPerformance(250, 500);
    }
}
