import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComplexNumberTest {
    @Test
    void testCantor() {
        assertEquals(4, new ComplexNumber().cantorValue(1, 1));
        assertEquals(79, new ComplexNumber(1, 1).toInteger());
        assertEquals(1, new ComplexNumber().toInteger());
    }
}