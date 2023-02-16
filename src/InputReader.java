import javax.print.attribute.standard.MediaSize;
import java.io.*;
import java.util.concurrent.LinkedBlockingDeque;

public class InputReader {
    private static final String inputFile = "data/input.txt";

    /**
     * Зчитує комплексні числа з файлу input
     * @return масив комплексних чисел у файлі, якщо зчитування пройшло успішно. Стандартний масив вхідних даних - інакше
     */
    public static ComplexNumber[] readNumbers() {
        ComplexNumberQueue numbers = new ComplexNumberQueue();
        int lineNumber = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            while (line != null) {
                lineNumber++;
                try {
                    ComplexNumber toAdd = parseComplexNumber(line);
                    if (toAdd != null)
                        numbers.push(toAdd);
                } catch (Exception e) {
                    OutputWriter.logError("An error occurred during reading line " + lineNumber);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            OutputWriter.logError("An error occurred during reading line " + lineNumber);
        }
        return numbers.toComplexNumbers();

    }

    private static ComplexNumber[] defaultList() {
        ComplexNumber[] defaultList = new ComplexNumber[8];
        defaultList[0] = new ComplexNumber(20, 100);
        defaultList[1] = new ComplexNumber(-86, 2);
        defaultList[2] = new ComplexNumber(0, 0);
        defaultList[3] = new ComplexNumber(4, 8);
        defaultList[4] = new ComplexNumber(-2, -112);
        defaultList[5] = new ComplexNumber(123, 0);
        defaultList[6] = new ComplexNumber(0, 123);
        defaultList[7] = new ComplexNumber(9, -9);
        return defaultList;
    }
    protected static ComplexNumber parseComplexNumber(String line) {
        return line.matches("[+-]?\\d+\s+[+-]?\\d+") ? defaultRead(line) : advancedRead(line);
    }
    private static ComplexNumber defaultRead(String line) {
        String[] lines = line.trim().split("\s+");
        assert lines.length == 2;
        return new ComplexNumber(Integer.parseInt(lines[0]), Integer.parseInt(lines[1]));
    }
    private static ComplexNumber advancedRead(String line) {
        line = line.replaceAll("\\s+", "");

        line = line.replaceAll("-", "+-");
        String[] parts = line.split("\\+");
        int real = 0;
        int imaginary = 0;
        for (String number : parts) {
            if (number.isEmpty())
                continue;
            if (number.contains("i")) {
                number = number.replaceAll("(\\*?i)|(8\\*?)", "");
                if (number.isEmpty()) {
                    imaginary += 1;
                } else if (number.equals("-")){
                    imaginary -= 1;
                } else {
                    imaginary += Integer.parseInt(number);
                }
            }
            else {
                real += Integer.parseInt(number);
            }
        }
        return new ComplexNumber(real, imaginary);
    }
}
