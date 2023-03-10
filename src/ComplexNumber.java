public class ComplexNumber implements Hashable{

    private final int real; //дійсна частина числа
    private final int imaginary; //уявна частина числа
    private final int group; //знакова група
    // 0 - (++)
    // 1 - (+-)
    // 2 - (-+)
    // 3 - (--)

    /**
     *
     * @param real - дійсна частина числа
     * @param imaginary - уявна частина числа
     * Створює число real + imaginary * i.
     */
    public ComplexNumber(int real, int imaginary) {
        this.real = real;
        this.imaginary = imaginary;
        if (real >= 0 && imaginary >= 0) {
            group = 0;
        } else if (real < 0 && imaginary >= 0) {
            group = 1;
        } else if (real < 0) { //imaginary < 0
            group = 2;
        } else { //real >= 0 imaginary < 0
            group = 3;
        }
    }

    /**
     * Створює число 0
     */
    public ComplexNumber() {
        real = 0;
        imaginary = 0;
        group = 0;
    }

    public ComplexNumber(int key) {
        int g = key % 4;
        key -= g;
        key /= 4;
        boolean found = false;
        int i = -1;
        int a = -1;
        int b = -1;
        while (a == -1) {
            i++;
            for (int j = 0; j < key; j++) {
                if (pairingFunction(i, j) == key) {
                    a = i;
                    b = j;
                }
            }
        }
        if (g == 1) {
            a *= -1;
        } else if (g == 2) {
            a *= -1;
            b *= -1;
        } else if (g == 3) {
            b *= -1;
        }
        this.real = a;
        this.imaginary = b;
        this.group = g;
    }

    /**
     *
     * @param obj - інше комплексне число
     * @return true, якщо дійсна та уявна частина рівні
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof ComplexNumber other))
            return false;

        return (other.real == this.real) && (other.imaginary == this.imaginary);

    }
    @Override
    public String toString() {
       if (real == 0 && imaginary == 0)
           return "0";
       if (real == 0)
           return printImaginary();
       if (imaginary == 0)
           return printReal();
       if (imaginary > 0)
           return printReal() + "+" + printImaginary();
       else
           return printReal() + printImaginary();
    }
    private String printImaginary() {
        if (imaginary == 1)
            return "i";
        if (imaginary == -1)
            return "-i";
        return imaginary + "*i";
    }
    private String printReal() {
        return real + "";
    }

    /**
     *
     * @return унікальне цілочисельне значення, що базується на взаємно-однозначній відповідності пар чисел та цілих.
     */
    public int toInteger() {
        int x = Math.abs(real);
        int y = Math.abs(imaginary);
        return pairingFunction(x, y) * 4 + group;
    }

    /**
     *
     * @param a - перша координата
     * @param b - друга координата
     * @return номер пари в нумерації Кантора
     */
    protected int pairingFunction(int a, int b) {
        return (a+b)*(a+b+1)/2+a;
    }
    @Override
    public int hashCode() {
        return toInteger();
    }

    public int hash(int a, int b, int p, int m) {
       return ((a * this.toInteger() + b) % p) % m;
    }
}
