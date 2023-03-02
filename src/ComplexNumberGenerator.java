
import java.util.Random;

public class ComplexNumberGenerator {
    public static final int LIMIT = (int) Math.sqrt(Integer.MAX_VALUE);
    //значення ключа, при якому хеш гарантовано не виходить за INT_MAX
    public final int MAX_BOUND = 150; //значення суми компонент, при якому хеш гарантовано не виходить за INT_MAX
    private final Random random;
    public ComplexNumberGenerator() {
        random = new Random();
    }

    /**
     *
     * @param number - кількість комплексних чисел, яку необхідно згенерувати
     * @return масив випадкових допустимих комплексних чисел
     */
    public ComplexNumber[] generate(int number) {
        ComplexNumber[] arr = new ComplexNumber[number];
        for (int i = 0; i < number; i++) {
            int real = getInt(MAX_BOUND);
            int imaginary = getInt(MAX_BOUND-Math.abs(real));
            arr[i] = new ComplexNumber(real, imaginary);
        }
        return arr;
    }
    public ComplexNumberArray[] generateArrays(int number) {
       return generateArrays(number, 100);
    }
    public ComplexNumberArray[] generateArrays(int number, int maxSize) {
        ComplexNumberArray[] arr = new ComplexNumberArray[number];
        for (int i = 0; i < number; i++) {
            int size = random.nextInt(2, maxSize+1);
            arr[i] = new ComplexNumberArray(generate(size));
        }
        return arr;
    }


    /**
     *
     * @param limit - діапазон значень
     * @return випадкове значення [-limit, limit]
     */
    private int getInt(int limit) {
        int absoluteLimit = Math.abs(limit);
        return absoluteLimit < 1 ? 0 : random.nextInt(-absoluteLimit, absoluteLimit+1);
    }
}
