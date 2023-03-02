public class ComplexNumberStack implements ComplexNumberList {
    private ListNode top; //верхній елемент стеку
    private int size; //кількість елементів стеку

    public ComplexNumberStack() { //створення порожнього стеку
        size = 0;
        top = null;
    }

    public ComplexNumberStack(Hashable[] array) { //створення стеку з масиву
        if (array != null)
            for (Hashable a : array) {
                push(a);
            }
    }
    @Override
    public void push(Hashable number) { //додання елементу до стеку
        this.top = new ListNode(number, top);
        size++;
    }

    @Override
    public Hashable pop() {
        if (this.top == null)
            return null;
        Hashable n = this.top.value();
        this.top = top.next();
        size--;
        return n;
    }

    public ComplexNumberContainer[] toContainers() { //перетворення стеку в масив контейнерів комплексних чисел
        ComplexNumberContainer[] result = new ComplexNumberContainer[size];
        int i = 0;
        for (ListNode current = top; current != null; current = current.next(), i++) {
            result[i] = new ComplexNumberContainer(current.value());
        }
        return result;
    }
    public Hashable[] toComplexNumbers() { //перетворення стеку в масив комплексних чисел
        Hashable[] result = new Hashable[size];
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
