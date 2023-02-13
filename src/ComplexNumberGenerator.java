
import java.util.Random;

public class ComplexNumberGenerator {
    private final Random random;
    public ComplexNumberGenerator() {
        random = new Random();
    }

    public ComplexNumber[] generate(int number) {
        ComplexNumber[] arr = new ComplexNumber[number];
        for (int i = 0; i < number; i++) {
            int real = getInt(MAX_BOUND);
            int imaginary = getInt(MAX_BOUND-Math.abs(real));
            arr[i] = new ComplexNumber(real, imaginary);
        }
        return arr;
    }

    public final int MAX_BOUND = 150;
    private int getInt(int limit) {
        int result = limit < 1 ? 0 :random.nextInt(limit);
        result *= Math.pow(-1, random.nextInt(2));
        return result;
    }
}
