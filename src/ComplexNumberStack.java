public class ComplexNumberStack {
    private ListNode top; //верхній елемент стеку
    private int size; //кількість елементів стеку

    public ComplexNumberStack() { //створення порожнього стеку
        size = 0;
        top = null;
    }

    public ComplexNumberStack(ComplexNumber[] array) { //створення стеку з масиву
        if (array != null)
            for (ComplexNumber a : array) {
                push(a);
            }
    }

    public void push(ComplexNumber number) { //додання елементу до стеку
        this.top = new ListNode(number, top);
        size++;
    }
    public ComplexNumberContainer[] toContainers() { //перетворення стеку в масив контейнерів комплексних чисел
        ComplexNumberContainer[] result = new ComplexNumberContainer[size];
        int i = 0;
        for (ListNode current = top; current != null; current = current.next(), i++) {
            result[i] = new ComplexNumberContainer(current.value());
        }
        return result;
    }
    public ComplexNumber[] toComplexNumbers() { //перетворення стеку в масив комплексних чисел
        ComplexNumber[] result = new ComplexNumber[size];
        int i = 0;
        for (ListNode current = top; current != null; current = current.next(), i++) {
            result[i] = current.value();
        }
        return result;
    }

    public int size() {
        return size;
    } //розмір стеку
}
