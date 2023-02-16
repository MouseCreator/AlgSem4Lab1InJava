public class ComplexNumberContainer implements Container {
    private ComplexNumber number; //вміст контейнера
    public ComplexNumberContainer(ComplexNumber n) {
        this.number = n;
    }

    /**
     *
     * @param tLevel - рівень табуляції
     * @return текстове значення вмісту контейнера
     */
    @Override
    public String print(int tLevel) {
        return number.toString() + "\n";
    }

    /**
     *
     * @param value - значення, що необхідно помістити в контейнер (попереднє буде видалено)
     */
    @Override
    public void insert(ComplexNumber value) {
        number = value;
    }

    /**
     *
     * @param pushTo - стек, у який помістить значення в контейнері
     */
    @Override
    public void pickHashedData(ComplexNumberStack pushTo) {
        pushTo.push(number);
    }

    /**
     *
     * @return вміст контейнера
     */
    public ComplexNumber get() {
        return number;
    }
}
