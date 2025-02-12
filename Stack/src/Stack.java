public class Stack {
    private Node top;
    private int height;
    public Stack(int value) {
        Node newNode = new Node(value);
        top = newNode;
        height = 1;
    }
    public void push(int value) {
        Node newNode  = new Node(value);
        if (top == null) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
        height++;
    }

    public void printStack() {
        Node current = top;
        while (current != null) {
            System.out.println(current.value);
            System.out.println("↓");
            current = current.next;
        }
    }

    public Node pop() {
        if (top == null) return null;
        Node current = top;
        top = current.next;
        current.next = null;
        height--;
        return current;
    }
}
