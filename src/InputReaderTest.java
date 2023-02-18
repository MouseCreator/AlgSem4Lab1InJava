import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputReaderTest {
    @Test
    void ParserDefaultTest() {
        assertEquals(InputReader.parseComplexNumber("1 10"), new ComplexNumber(1, 10));
        assertEquals(InputReader.parseComplexNumber("10"), new ComplexNumber(10, 0));
        assertEquals(InputReader.parseComplexNumber("1  -8"), new ComplexNumber(1, -8));
        assertEquals(InputReader.parseComplexNumber("4         10"), new ComplexNumber(4, 10));
        assertEquals(InputReader.parseComplexNumber("-1 -10"), new ComplexNumber(-1, -10));
    }

    @Test
    void ParserAdvancedTest() {
        assertEquals(InputReader.parseComplexNumber("1+i"), new ComplexNumber(1, 1));
        assertEquals(InputReader.parseComplexNumber("3+7i"), new ComplexNumber(3, 7));
        assertEquals(InputReader.parseComplexNumber("9i"), new ComplexNumber(0, 9));
        assertEquals(InputReader.parseComplexNumber("1+i"), new ComplexNumber(1, 1));
        assertEquals(InputReader.parseComplexNumber("-3-4*i"), new ComplexNumber(-3, -4));
        assertEquals(InputReader.parseComplexNumber("20i+10+40i"), new ComplexNumber(10, 60));
        assertEquals(InputReader.parseComplexNumber("2+i10"), new ComplexNumber(2, 10));
        assertEquals(InputReader.parseComplexNumber("i"), new ComplexNumber(0, 1));
        assertEquals(InputReader.parseComplexNumber("4"), new ComplexNumber(4, 0));
        assertEquals(InputReader.parseComplexNumber("-7"), new ComplexNumber(-7, 0));
        assertEquals(InputReader.parseComplexNumber("-i"), new ComplexNumber(0, -1));
    }

}