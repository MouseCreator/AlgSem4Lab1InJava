public interface ComplexNumberList {
    void push(ComplexNumber number);
    ComplexNumber pop();
    ComplexNumberContainer[] toContainers();
    ComplexNumber[] toComplexNumbers();

    int size();
}
