
public class Main {
    //початок виконання програми
    public static void main(String[] args) {
        OutputWriter.clear(); //підготувати вихідний файл

        //Для звичайних комплексних чисел
        Hashable[] toHash = InputReader.readNumbers(); //зчитати вхідні дані
        HashTable table = new HashTable(); //створити хеш-таблицю
        table.hash(toHash); //покласти дані у хеш-таблицю


        //Для масивів комплексних чисел
        Hashable[] array = InputReader.readArrays();
        HashTable tableForArray = new HashTable();
        tableForArray.hash(array);


        //Вивід результатів
        OutputWriter.write("\n\n=== РЕЗУЛЬТАТИ ===");
        OutputWriter.write("Комплексні числа:");
        OutputWriter.write(table.print(0)); //вивести хеш-таблицю у вихідний файл

        OutputWriter.write("Масиви комплексних чисел:");
        OutputWriter.write(tableForArray.print(0));
    }
}