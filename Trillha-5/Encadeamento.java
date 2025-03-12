import java.util.LinkedList;

class HashTable {
    private static final int TABLE_SIZE = 10;
    private LinkedList<Entry>[] table;

    static class Entry {
        int key;
        String value;

        Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public HashTable() {
        table = new LinkedList[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(int key) {
        return key % TABLE_SIZE;
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

    public void display() {
        for (int i = 0; i < TABLE_SIZE; i++) {
            System.out.print("Index " + i + ": ");
            for (Entry entry : table[i]) {
                System.out.print("(" + entry.key + ", " + entry.value + ") ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable();
        hashTable.insert(10, "Alice");
        hashTable.insert(20, "Bob");
        hashTable.insert(15, "Charlie");
        hashTable.insert(25, "Dave");
        
        System.out.println("Search key 15: " + hashTable.search(15));
        hashTable.remove(15);
        System.out.println("Search key 15 after removal: " + hashTable.search(15));
        
        hashTable.display();
    }
}
