public class Main {
    public static void main(String[] args) {
        OutputWriter.clear(); //підготувати вихідний файл
        ComplexNumber[] toHash = InputReader.readNumbers(); //зчитати вхідні дані
        HashTable table = new HashTable(); //створити хеш-таблицю
        table.hash(toHash); //покласти дані у хеш-таблицю
        OutputWriter.write(table.print(0)); //вивести хеш-таблицю у вихідний файл
    }
}