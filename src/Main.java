public class Main {
    public static void main(String[] args) {
        ComplexNumber[] toHash = InputReader.readNumbers();
        HashTable table = new HashTable(toHash);
        OutputWriter.write(table.print(0));
    }
}