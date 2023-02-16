
import java.util.Random;

public class ComplexNumberGenerator {
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

    public final int MAX_BOUND = 150; //значення суми компонент, при якому хеш гарантовано не виходить за INT_MAX.

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
