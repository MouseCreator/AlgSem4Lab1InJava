import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Клас, який відповідає за вивід інформації у вихідні файли: тестові дені, хеш-таблиці, повідомлення про помилки
 */
public class OutputWriter {
    private static final String outputFile = "data/output.txt"; //файл для виводу інформації
    private static final String testFile = "data/test.txt"; //файл для виводу тестів

    static SimpleDateFormat formatter = new SimpleDateFormat("ss:mm:hh dd-MM-yyyy");

    /**
     * Метод для записування у файл повідомлення про помилку
     * @param errorMessage - помилка, що сталася
     */
    public static void logError(String errorMessage) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));
            writer.write("[" + formatter.format(new Date()) + "]\n"); //записати час і дату помилки
            writer.write(errorMessage); //записати помилку
            writer.newLine(); //додати новий рядок
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Записує інформацію у вивідний файл. Попередні записи будуть збережені
     * @param message - повідомлення для виводу
     */
    public static void write(String message) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));
            writer.write(message); //записати повідомлення
            writer.newLine(); //додати новий рядок
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Записує інформацію у файл для тестів. Попередні записи будуть видалені
     * @param message - повідомлення для запису
     */
    public static void writeAsTest(String message) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(testFile));
            writer.write(message); //записати у тестовий файл
            writer.newLine(); //додати новий рядок
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Очистити вивідний файл
     */
    public static void clear() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, false));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
