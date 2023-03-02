
public interface ComplexNumberList {
    void push(Hashable number);
    Hashable pop();
    ComplexNumberContainer[] toContainers();
    Hashable[] toComplexNumbers();

    int size();
}
