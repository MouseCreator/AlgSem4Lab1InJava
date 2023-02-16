public class ComplexNumberQueue {
    private ListNode head; //верхній елемент стеку

    private ListNode tail;
    private int size; //кількість елементів стеку

    public ComplexNumberQueue() { //створення порожнього стеку
        size = 0;
        head = null;
        tail = null;
    }

    public ComplexNumberQueue(ComplexNumber[] array) { //створення стеку з масиву
        if (array != null)
            for (ComplexNumber a : array) {
                push(a);
            }
    }

    public void push(ComplexNumber number) { //додання елементу до стеку
        tail = new ListNode(number, tail);
        if (head == null)
            head = tail;
        size++;
    }
    public ComplexNumber pop() { //додання елементу до стеку
        if (size == 0)
            return null;
        ComplexNumber n = head.getValue();
        head = head.getNext();
        if (head == null)
            tail = null;
        size--;
        return n;
    }
    public ComplexNumberContainer[] toContainers() { //перетворення черги в масив контейнерів комплексних чисел
        ComplexNumberContainer[] result = new ComplexNumberContainer[size];
        int i = 0;
        for (ListNode current = tail; current != null; current = current.getNext(), i++) {
            result[i] = new ComplexNumberContainer(current.getValue());
        }
        return result;
    }
    public ComplexNumber[] toComplexNumbers() { //перетворення черги в масив комплексних чисел
        ComplexNumber[] result = new ComplexNumber[size];
        int i = 0;
        for (ListNode current = tail; current != null; current = current.getNext(), i++) {
            result[i] = current.getValue();
        }
        return result;
    }

    public int size() {
        return size;
    } //розмір черги
}
