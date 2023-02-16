import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OutputWriter {
    private static final String outputFile = "data/output.txt";
    private static final String testFile = "data/test.txt";
    private static final String errorFile = "data/error.txt";

    static SimpleDateFormat formatter = new SimpleDateFormat("ss:mm:hh dd-MM-yyyy");
    public static void logError(String errorMessage) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(errorFile, true));
            writer.write("[" + formatter.format(new Date()) + "]\n");
            writer.write(errorMessage);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void write(String message) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write(message);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void writeAsTest(String message) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(testFile));
            writer.write(message);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
