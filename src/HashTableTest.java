import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
                    int code = hash(c, i, j, p);
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
    private int hash(ComplexNumber c, int a, int b, int p) {
        return ((c.toInteger() * a + b) % p) % 16;
    }

    @Test
    void inActionTest() {
        ComplexNumberGenerator generator = new ComplexNumberGenerator();
        ComplexNumber[] arr = generator.generate(100);
        HashTable table = new HashTable();
        table.hash(arr);
        OutputWriter.write(table.print(0));
    }
}