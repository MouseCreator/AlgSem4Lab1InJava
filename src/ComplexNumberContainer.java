
public class ComplexNumberContainer implements Container {
    private Hashable number; //вміст контейнера
    public ComplexNumberContainer(Hashable n) {
        this.number = n;
    }

    /**
     *
     * @param tLevel - рівень табуляції
     * @return текстове значення вмісту контейнера
     */
    @Override
    public String print(int tLevel) {
        return number.toString();
    }

    /**
     *
     * @param value - значення, що необхідно помістити в контейнер (попереднє буде видалено)
     */
    @Override
    public void insert(Hashable value) {
        number = value;
    }

    @Override
    public Hashable get(Hashable v) {
        return v.equals(number) ? number : null;
    }

    /**
     *
     * @param pushTo - стек, у який помістить значення в контейнері
     */
    @Override
    public void pickHashedData(ComplexNumberList pushTo) {
        pushTo.push(number);
    }

    /**
     *
     * @return вміст контейнера
     */
    public Hashable get() {
        return number;
    }
    @Override
    public String toString() {
        return "[" + number.toString() + "]";
    }
}
