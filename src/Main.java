public class Main {
    public static void main(String[] args) {
        ComplexNumber[] toHash = InputReader.readNumbers();
        HashTable table = new HashTable(126593270991177L);
        table.hash(toHash);
        OutputWriter.write(table.print(0));
    }
}