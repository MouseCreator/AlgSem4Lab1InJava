public class NumberContainer implements Container {
    private ComplexNumber number;
    public NumberContainer() {
        number = new ComplexNumber();
    }
    public NumberContainer(ComplexNumber n) {
        this.number = n;
    }
    @Override
    public void insert(ComplexNumber value) {
        number = value;
    }

    public ComplexNumber get() {
        return number;
    }
}
