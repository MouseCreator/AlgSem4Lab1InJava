import java.io.*;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputReader {
    private static final String inputFile = "input.txt";
    public static ComplexNumber[] readNumbers() {
        LinkedList<ComplexNumber> numbersContainer = new LinkedList<>();
        int lineNumber = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(inputFile)));
            String line = reader.readLine();
            while (line != null) {
                lineNumber++;
                numbersContainer.push(parseComplexNumber(line));
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            OutputWriter.logError("Cannot find input.txt file");
        } catch (IOException e) {
            OutputWriter.logError("An error occurred during reading line " + lineNumber);
        }
        return new ComplexNumber[3];
    }
    private static ComplexNumber parseComplexNumber(String line) {
        Pattern pattern = Pattern.compile("");
        Matcher matcher = pattern.matcher(line);
        return matcher.matches() ? defaultRead(line) : advancedRead(line);
    }

    private static ComplexNumber defaultRead(String line) {
        return new ComplexNumber();
    }
    private static ComplexNumber advancedRead(String line) {
        return new ComplexNumber();
    }

}
