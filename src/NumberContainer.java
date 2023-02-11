public class NumberContainer implements Container {
    private ComplexNumber number;
    public NumberContainer(ComplexNumber n) {
        this.number = n;
    }

    @Override
    public String print(int tLevel) {
        return tabulation(tLevel) + number.toString() + "\n";
    }
    public String tabulation(int tLevel) {
        return "\t".repeat(Math.max(0, tLevel));
    }

    @Override
    public void insert(ComplexNumber value) {
        number = value;
    }

    public ComplexNumber get() {
        return number;
    }
}
