public class ListNode {
    private ListNode next;
    private ComplexNumber value;
    public ListNode() {
        value = new ComplexNumber();
        next = null;
    }
    public ListNode(ComplexNumber value) {
        this.value = value;
        next = null;
    }
    public ListNode(ComplexNumber value, ListNode next) {
        this.value = value;
        this.next = next;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public ComplexNumber getValue() {
        return value;
    }

    public void setValue(ComplexNumber value) {
        this.value = value;
    }
}
