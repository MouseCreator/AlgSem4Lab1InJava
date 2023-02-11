public class ComplexNumber {

    private final int real;
    private final int imaginary;

    public ComplexNumber(int real, int imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public ComplexNumber() {
        real = 0;
        imaginary = 0;
    }

    public int re() {
        return real;
    }
    public int im() {
        return imaginary;
    }
    public double module() {
        return Math.sqrt(real*real + imaginary*imaginary);
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
        return this.real + "" + this.imaginary + "*i";
    }
}
