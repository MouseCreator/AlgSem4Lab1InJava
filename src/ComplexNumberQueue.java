/**
 * Черга, яка містить комплексні числа.
 * Використовується для тимчасового зберігання комплексних чисел.
 */
public class ComplexNumberQueue {
    private ListNode head; //початок черги

    private ListNode tail; //кінець черги
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
    public ComplexNumber pop() { //вилучення елемента з черги
        if (size == 0)
            return null;
        ComplexNumber n = head.value();
        head = head.next();
        if (head == null)
            tail = null;
        size--;
        return n;
    }
    public ComplexNumberContainer[] toContainers() { //перетворення черги в масив контейнерів комплексних чисел
        ComplexNumberContainer[] result = new ComplexNumberContainer[size];
        int i = 0;
        for (ListNode current = tail; current != null; current = current.next(), i++) {
            result[i] = new ComplexNumberContainer(current.value());
        }
        return result;
    }
    public ComplexNumber[] toComplexNumbers() { //перетворення черги в масив комплексних чисел
        ComplexNumber[] result = new ComplexNumber[size];
        int i = 0;
        for (ListNode current = tail; current != null; current = current.next(), i++) {
            result[i] = current.value();
        }
        return result;
    }

    public int size() {
        return size;
    } //розмір черги
}
