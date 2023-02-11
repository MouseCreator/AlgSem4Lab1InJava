public class NumberContainer implements Container {
    private ComplexNumber number;
    public NumberContainer(ComplexNumber n) {
        this.number = n;
    }

    @Override
    public String print(int tLevel) {
        return number.toString() + "\n";
    }
    @Override
    public void insert(ComplexNumber value) {
        number = value;
    }

    public ComplexNumber get() {
        return number;
    }
}
