/**
 * Черга, яка містить комплексні числа.
 * Використовується для тимчасового зберігання комплексних чисел.
 */
public class ComplexNumberQueue implements ComplexNumberList{
    private ListNode head; //початок черги

    private ListNode tail; //кінець черги
    private int size; //кількість елементів стеку

    public ComplexNumberQueue() { //створення порожньої черги
        size = 0;
        head = null;
        tail = null;
    }

    public ComplexNumberQueue(Hashable[] array) { //створення черги з масиву
        if (array != null)
            for (Hashable a : array) {
                push(a);
            }
    }

    public void push(Hashable number) { //додання елементу до черги
        tail = new ListNode(number, tail);
        if (head == null)
            head = tail;
        size++;
    }
    public Hashable pop() { //вилучення елемента з черги
        if (size == 0)
            return null;
        Hashable n = head.value();
        head = head.next();
        if (head == null)
            tail = null;
        size--;
        return n;
    }
    @Override
    public ComplexNumberContainer[] toContainers() { //перетворення черги в масив контейнерів комплексних чисел
        ComplexNumberContainer[] result = new ComplexNumberContainer[size];
        int i = 0;
        for (ListNode current = tail; current != null; current = current.next(), i++) {
            result[i] = new ComplexNumberContainer(current.value());
        }
        return result;
    }
    @Override
    public Hashable[] toComplexNumbers() { //перетворення черги в масив комплексних чисел
        Hashable[] result = new Hashable[size];
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
