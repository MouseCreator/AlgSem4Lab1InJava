import java.util.Arrays;

public class ComplexNumberArray implements Hashable{
    private final ComplexNumber[] array;
    @Override
    public int hash(int a, int b, int p, int m) {
        int hash = array.length;
        for (ComplexNumber c : array) {
            hash = (hash + a * c.toInteger() + b) % p;
        }
        return hash % m;
    }

    @Override
    public int toInteger() {
        int max = 0;
        for (ComplexNumber a : array) {
            max = Math.max(max, a.toInteger());
        }
        return max;
    }

    public ComplexNumberArray(ComplexNumber[] array) {
        this.array = array;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

}
