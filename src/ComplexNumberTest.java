import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ComplexNumberTest {
    @Test
    void testCantor() {
        int[] arr = new int[200*200];
        int k = 0;
        for (int i = -100; i < 100; i++) {
            for (int j = -100; j < 100; j++) {
               arr[k++] = new ComplexNumber(i, j).toInteger();
               System.out.println(i + " " + j + " " + arr[k-1]);
            }
        }
        assertEquals(Arrays.stream(arr).distinct().toArray().length, arr.length);
    }
}