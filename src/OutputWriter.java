import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OutputWriter {
    private static final String outputFile = "data/output.txt";
    public static void logError(String errorMessage) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write(errorMessage);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
