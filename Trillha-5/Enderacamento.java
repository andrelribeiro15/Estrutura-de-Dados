class HashTableLinearProbing {
    private static final int TABLE_SIZE = 10;
    private Entry[] table;
    private int size;

    static class Entry {
        int key;
        String value;

        Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public HashTableLinearProbing() {
        table = new Entry[TABLE_SIZE];
        size = 0;
    }

    private int hash(int key) {
        return key % TABLE_SIZE;
    }

    public void insert(int key, String value) {
        if (size == TABLE_SIZE) {
            System.out.println("Tabela cheia! Não é possível inserir mais elementos.");
            return;
        }

        int index = hash(key);
        while (table[index] != null) {
            index = (index + 1) % TABLE_SIZE;
        }
        table[index] = new Entry(key, value);
        size++;
    }

    public String search(int key) {
        int index = hash(key);
        int startIndex = index;
        while (table[index] != null) {
            if (table[index].key == key) {
                return table[index].value;
            }
            index = (index + 1) % TABLE_SIZE;
            if (index == startIndex) {
                break;
            }
        }
        return null;
    }

    public void remove(int key) {
        int index = hash(key);
        int startIndex = index;
        while (table[index] != null) {
            if (table[index].key == key) {
                table[index] = null;
                size--;
                rehash();
                return;
            }
            index = (index + 1) % TABLE_SIZE;
            if (index == startIndex) {
                break;
            }
        }
    }

    private void rehash() {
        Entry[] oldTable = table;
        table = new Entry[TABLE_SIZE];
        size = 0;
        for (Entry entry : oldTable) {
            if (entry != null) {
                insert(entry.key, entry.value);
            }
        }
    }

    public void display() {
        for (int i = 0; i < TABLE_SIZE; i++) {
            if (table[i] != null) {
                System.out.println("Index " + i + ": (" + table[i].key + ", " + table[i].value + ")");
            } else {
                System.out.println("Index " + i + ": [Vazio]");
            }
        }
    }

    public static void main(String[] args) {
        HashTableLinearProbing hashTable = new HashTableLinearProbing();
        hashTable.insert(10, "Alice");
        hashTable.insert(20, "Bob");
        hashTable.insert(15, "Charlie");
        hashTable.insert(25, "Dave");
        hashTable.insert(5, "Eve");
        hashTable.insert(35, "Frank");
        hashTable.insert(45, "Grace");
        hashTable.insert(55, "Hank");
        hashTable.insert(65, "Ivy");
        hashTable.insert(75, "Jack");
        
        System.out.println("Busca pela chave 15: " + hashTable.search(15));
        hashTable.remove(15);
        System.out.println("Busca pela chave 15 após remoção: " + hashTable.search(15));
        
        hashTable.display();
    }
}
