public class NumberContainer implements Container {
    private ComplexNumber number;

    @Override
    public void insert(ComplexNumber value) {
        number = value;
    }
}
