import java.io.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputReader {
    private static final String inputFile = "data/input.txt";
    public static ComplexNumber[] readNumbers() {
        LinkedBlockingDeque<ComplexNumber> numbersContainer = new LinkedBlockingDeque<>();
        int lineNumber = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
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
        return toNumbers(numbersContainer);

    }
    private static ComplexNumber[] toNumbers(LinkedBlockingDeque<ComplexNumber> input) {
        if (input.isEmpty())
            return defaultList();
        else {
            ComplexNumber[] array = new ComplexNumber[input.size()];
            for (int i = 0; i < input.size(); i++) {
                array[i] = input.pop();
            }
            return array;
        }
    }
    private static ComplexNumber[] defaultList() {
        ComplexNumber[] defaultList = new ComplexNumber[8];
        defaultList[0] = new ComplexNumber(200, 100);
        defaultList[1] = new ComplexNumber(-86, 2);
        defaultList[2] = new ComplexNumber(0, 0);
        defaultList[3] = new ComplexNumber(4, 8);
        defaultList[4] = new ComplexNumber(-2, -777);
        defaultList[5] = new ComplexNumber(123, 0);
        defaultList[6] = new ComplexNumber(0, 123);
        defaultList[7] = new ComplexNumber(9, -9);
        return defaultList;
    }
    private static ComplexNumber parseComplexNumber(String line) {
        Pattern pattern = Pattern.compile("[0-9]*\s[0-9]*");
        Matcher matcher = pattern.matcher(line);
        return matcher.matches() ? defaultRead(line) : advancedRead(line);
    }

    private static ComplexNumber defaultRead(String line) {
        String[] lines = line.trim().split("\s");
        assert lines.length == 2;
        return new ComplexNumber(Integer.parseInt(lines[0]), Integer.parseInt(lines[1]));
    }
    private static ComplexNumber advancedRead(String line) {
        line = line.replaceAll("\s", "");
        int real = 0;
        int imaginary = 0;
        boolean isImaginaryPart = false;
        StringBuilder temp = new StringBuilder();
        for (char ch : line.toCharArray()) {
            switch (ch) {
                case 'i':
                    isImaginaryPart = true;
                    break;
                case '+':
                    if (isImaginaryPart) {

                    }
                    isImaginaryPart = false;
                    break;
                case '-':
                    if (isImaginaryPart) {

                    }
                    isImaginaryPart = false;
                    temp.append(ch);
                    break;

            }
        }

        return new ComplexNumber(real, imaginary);
    }

}
