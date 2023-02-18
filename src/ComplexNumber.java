public class ComplexNumber {

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
        } else if (real < 0) { //imaginary >= 0
            group = 2;
        } else { //real >= 0 imaginary >= 0
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
}
