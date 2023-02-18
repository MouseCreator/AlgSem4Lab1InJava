import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InputReader {
    private static final String inputFile = "data/input.txt";

    /**
     * Зчитує комплексні числа з файлу input з розрахунком, що кожне число написане з нового радка
     * @return масив комплексних чисел у файлі, якщо зчитування пройшло успішно. Стандартний масив вхідних даних - інакше
     */
    public static ComplexNumber[] readNumbers() {
        ComplexNumberQueue numbers = new ComplexNumberQueue();
        int lineNumber = 0; //підрахунок рядків (для точного вказання місця помилки)
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile)); //відкрити файл
            String line = reader.readLine();
            while (line != null) {
                lineNumber++;
                if (!line.isEmpty()) {
                    try {
                        ComplexNumber toAdd = parseComplexNumber(line);
                        numbers.push(toAdd);
                    } catch (Exception e) {
                        //повідомити про помилку у рядку
                        OutputWriter.logError("An error occurred during reading line " + lineNumber);
                    }
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            //повідомити про помилку у файлі
            OutputWriter.logError("Cannot open input file. Default values will be used instead.");
            return defaultList();
        }
        return numbers.toComplexNumbers();

    }

    /**
     *
     * @return приклад списку комплексних чисел
     */
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

    /**
     *
     * @param line - рядок з записом комплексного числа
     * @return комплексне число у рядку
     */
    protected static ComplexNumber parseComplexNumber(String line) {
        return line.matches("[+-]?\\d+\s+[+-]?\\d+") ? defaultRead(line) : advancedRead(line);
    }

    /**
     *
     * @param line - рядок у форматі "A B"
     * @return число A+B*i
     */
    private static ComplexNumber defaultRead(String line) {
        String[] lines = line.trim().split("\s+");
        assert lines.length == 2;
        return new ComplexNumber(Integer.parseInt(lines[0]), Integer.parseInt(lines[1]));
    }

    /**
     *
     * @param line - рядок у форматі "A+B*i" чи подібних
     * @return число A+B*i
     */
    private static ComplexNumber advancedRead(String line) {

        //поділ рядка-полінома на доданки
        line = line.replaceAll("\\s+", "");
        line = line.replaceAll("-", "+-");
        String[] parts = line.split("\\+");

        //обрахунок значення полінома
        int real = 0;
        int imaginary = 0;
        for (String number : parts) {
            if (number.isEmpty())
                continue;
            if (number.contains("i")) { //випадок, коли доданок є уявним
                number = number.replaceAll("(\\*?i)", "");
                if (number.isEmpty()) {
                    imaginary += 1;
                } else if (number.equals("-")){
                    imaginary -= 1;
                } else {
                    imaginary += Integer.parseInt(number);
                }
            }
            else { //випадок, коли доданок є дійсним
                real += Integer.parseInt(number);
            }
        }
        return new ComplexNumber(real, imaginary);
    }
}
