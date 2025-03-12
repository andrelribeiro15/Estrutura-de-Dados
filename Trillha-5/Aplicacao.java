import java.util.LinkedList;

class Dictionary {
    private static final int TABLE_SIZE = 26; // Para as letras do alfabeto
    private LinkedList<Entry>[] table;

    static class Entry {
        String word;
        String meaning;

        Entry(String word, String meaning) {
            this.word = word;
            this.meaning = meaning;
        }
    }

    public Dictionary() {
        table = new LinkedList[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(String word) {
        return Character.toLowerCase(word.charAt(0)) - 'a';
    }

    public void insert(String word, String meaning) {
        int index = hash(word);
        for (Entry entry : table[index]) {
            if (entry.word.equalsIgnoreCase(word)) {
                entry.meaning = meaning;
                return;
            }
        }
        table[index].add(new Entry(word, meaning));
    }

    public String search(String word) {
        int index = hash(word);
        for (Entry entry : table[index]) {
            if (entry.word.equalsIgnoreCase(word)) {
                return entry.meaning;
            }
        }
        return "Palavra não encontrada no dicionário.";
    }

    public void remove(String word) {
        int index = hash(word);
        table[index].removeIf(entry -> entry.word.equalsIgnoreCase(word));
    }

    public void display() {
        for (int i = 0; i < TABLE_SIZE; i++) {
            System.out.print((char) ('A' + i) + ": ");
            for (Entry entry : table[i]) {
                System.out.print("(" + entry.word + ", " + entry.meaning + ") ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        dictionary.insert("Apple", "Uma fruta vermelha ou verde");
        dictionary.insert("Banana", "Uma fruta amarela");
        dictionary.insert("Carro", "Um veículo motorizado");
        
        System.out.println("Significado de Apple: " + dictionary.search("Apple"));
        System.out.println("Significado de Gato: " + dictionary.search("Gato"));
        
        dictionary.remove("Apple");
        System.out.println("Significado de Apple após remoção: " + dictionary.search("Apple"));
        
        dictionary.display();
    }
}
