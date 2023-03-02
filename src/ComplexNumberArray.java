import java.util.Arrays;

/**
 * Масив деякої довжини, який потрібно захешувати
 */
public class ComplexNumberArray implements Hashable {
    private final Hashable[] array; //масив комплексних чисел

    public ComplexNumberArray(Hashable[] array) {
        this.array = array;
    }

    @Override
    public int hash(int a, int b, int p, int m) {
        int hash = array.length;
        for (Hashable c : array) {
            hash = (hash + a * c.toInteger() + b) % p;
        }
        return hash % m;
    }

    @Override
    public int toInteger() {
        int max = array.length;
        for (Hashable a : array) {
            max = Math.max(max, a.toInteger());
        }
        return max;
    }


    @Override
    public String toString() {
        return Arrays.toString(array);
    }

}
