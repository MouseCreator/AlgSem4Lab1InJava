public class Main {
    public static void main(String[] args) {
        OutputWriter.clear();
        ComplexNumber[] toHash = InputReader.readNumbers();
        HashTable table = new HashTable();
        table.hash(toHash);
        OutputWriter.write(table.print(0));
    }
}