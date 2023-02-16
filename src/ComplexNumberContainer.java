public class ComplexNumberContainer implements Container {
    private ComplexNumber number;
    public ComplexNumberContainer(ComplexNumber n) {
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

    @Override
    public void pickHashedData(ComplexNumberStack pushTo) {
        pushTo.push(number);
    }

    public ComplexNumber get() {
        return number;
    }
}
