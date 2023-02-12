import java.util.Random;

public class ComplexNumberGenerator {
    private final Random random;
    public ComplexNumberGenerator() {
        random = new Random();
    }

    public ComplexNumber[] generate(int number) {
        ComplexNumber[] arr = new ComplexNumber[number];
        for (int i = 0; i < number; i++) {
            int real = getInt();
            int imaginary = getInt();
            arr[i] = new ComplexNumber(real, imaginary);
        }
        return arr;
    }
    private int getInt() {
        int result = random.nextInt(100);
        result *= Math.pow(-1, random.nextInt());
        return result;
    }
}
