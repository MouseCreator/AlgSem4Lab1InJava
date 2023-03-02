import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

class HashTableTest {
    @Test
    void testCollisions() {
        ComplexNumber[] arr = new ComplexNumber[4];
        arr[0] = new ComplexNumber(1, 1);
        arr[1] = new ComplexNumber(2, 3);
        arr[2] = new ComplexNumber(3, 5);
        arr[3] = new ComplexNumber(4, 8);
        int p = 0;
        for (ComplexNumber c : arr) {
            p = Math.max(p, c.toInteger());
        }
        p = Primes.nextPrime(p);
        int FCount = 0;
        int TCount = 0;
        for (int i = 1; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                int[] memory = new int[4];
                int k = 0;
                for (ComplexNumber c : arr) {
                    int code = hash(c, i, j, p, 16);
                    memory[k++] = code;
                    System.out.print(code + "\t");
                }
                if (Arrays.stream(memory).distinct().toArray().length == arr.length) {
                    System.out.println("true");
                    TCount++;
                }
                else {
                    System.out.println("false");
                    FCount++;
                }
            }
        }
        System.out.println("TRUE: " + TCount + " FALSE: " + FCount);
    }

    @Test
    void testCollisionsOnLarge() {
        int size = 3;
        ComplexNumber[] arr = new ComplexNumber[size];
        arr[0] = new ComplexNumber(40, -19);
        arr[1] = new ComplexNumber(51, 54);
        arr[2] = new ComplexNumber(-25,-4);
        int p = 0;
        for (ComplexNumber c : arr) {
            System.out.println("Key: " + c.toInteger());
            p = Math.max(p, c.toInteger());
        }
        p = Primes.nextPrime(p);
        System.out.println("P: " + p);
        int FCount = 0;
        int TCount = 0;
        for (int i = 1; i < size*size; i++) {
            for (int j = 0; j < size*size; j++) {
                int[] memory = new int[size];
                int k = 0;
                for (ComplexNumber c : arr) {
                    int code = hash(c, i, j, p, size*size);
                    memory[k++] = code;
                    System.out.print(code + "\t");
                }
                if (Arrays.stream(memory).distinct().toArray().length == arr.length) {
                    System.out.println("true");
                    TCount++;
                }
                else {
                    System.out.println("false");
                    FCount++;
                }
            }
        }
        System.out.println("TRUE: " + TCount + " FALSE: " + FCount);
    }
    private int hash(ComplexNumber c, int a, int b, int p, int m) {
        return ((c.toInteger() * a + b) % p) % m;
    }

    @Test
    void inActionTest() {
        ComplexNumberGenerator generator = new ComplexNumberGenerator();
        ComplexNumber[] arr = generator.generate(100);
        HashTable table = new HashTable();
        table.hash(arr);
        OutputWriter.writeAsTest(table.print(0));
    }

    @Test
    void inActionTestOnArrays() {
        Random r = new Random();
        long seed = r.nextLong();
        ComplexNumberGenerator generator = new ComplexNumberGenerator();
        Hashable[] arr = generator.generateArrays(100);
        HashTable table = new HashTable(seed);
        table.hash(arr);
        OutputWriter.writeAsTest(table.print(0));

    }
}