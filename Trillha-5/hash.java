public class SimpleHash {
    private static final int TABLE_SIZE = 10;

    // Função de hash para inteiros
    public static int hashInt(int key) {
        return key % TABLE_SIZE;
    }

    // Função de hash para strings
    public static int hashString(String key) {
        int sum = 0;
        for (char c : key.toCharArray()) {
            sum += c; // Soma dos valores ASCII
        }
        return sum % TABLE_SIZE;
    }

    public static void main(String[] args) {
        int intKey = 123;
        String strKey = "Hashing";
        
        System.out.println("Hash para inteiro " + intKey + ": " + hashInt(intKey));
        System.out.println("Hash para string '" + strKey + "': " + hashString(strKey));
    }
}
