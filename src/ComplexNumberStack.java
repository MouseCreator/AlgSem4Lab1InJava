public class ComplexNumberStack {
    private ListNode top;
    private int size;

    public ComplexNumberStack() {
        size = 0;
        top = new ListNode();
    }

    public void push(ComplexNumber number) {
        this.top = new ListNode(number, top);
        size++;
    }
    public NumberContainer[] toContainers() {
        NumberContainer[] result = new NumberContainer[size];
        int i = 0;
        for (ListNode current = top; top != null; current = current.getNext(), i++) {
            result[i] = new NumberContainer(current.getValue());
        }
        return result;
    }

    public int size() {
        return size;
    }
}
