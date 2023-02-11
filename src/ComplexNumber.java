public class ComplexNumber {

    private final int real;
    private final int imaginary;
    private final int group;

    public ComplexNumber(int real, int imaginary) {
        this.real = real;
        this.imaginary = imaginary;
        if (real < 0 && imaginary < 0) {
            group = 0;
        } else if (real >= 0 && imaginary < 0) {
            group = 1;
        } else if (real < 0) {
            group = 2;
        } else {
            group = 3;
        }
    }

    public ComplexNumber() {
        real = 0;
        imaginary = 0;
        group = 1;
    }

    public int re() {
        return real;
    }
    public int im() {
        return imaginary;
    }

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
            return "-1";
        return imaginary + "*i";
    }
    private String printReal() {
        return real + "";
    }

    public int toInteger() {
        int x = Math.abs(real);
        int y = Math.abs(imaginary);
        return cantorValue(x, cantorValue(y, group));
    }
    private int cantorValue(int a, int b) {
        return (a * (a + 3 + 2 * b) + b * (1 + b)) / 2;
    }
}
