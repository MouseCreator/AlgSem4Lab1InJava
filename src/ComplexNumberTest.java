import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ComplexNumberTest {
    @Test
    void testCantor() {
        int N = 75;
        int[] arr = new int[4*N*N];
        int k = 0;
        int max = 0;
        for (int i = -N; i < N; i++) {
            for (int j = -N; j < N; j++) {
                int v = new ComplexNumber(i, j).toInteger();
                assertTrue(v >= 0);
                arr[k++] = v;
                max = Math.max(v, max);
            }
        }
        assertEquals(Arrays.stream(arr).distinct().toArray().length, arr.length);
        System.out.println(max);
        assertTrue(max < 46340);
    }
}