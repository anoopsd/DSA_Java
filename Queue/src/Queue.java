public class Queue {
    private Node first;
    private Node last;
    private int length;
    public Queue(int value) {
        Node newNode = new Node(value);
        first = newNode;
        last = newNode;
        length = 1;
    }

    public void printQueue() {
        Node current = first;
        while (current != null) {
            System.out.print(current.value + "->");
            current = current.next;
        }
    }

    public void enqueue(int value) {
        Node newNode = new Node(value);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        length++;
    }

    public Node dequeue() {
        if (first == null) return null;
        Node current = first;
        first = first.next;
        current.next = null;
        length--;
        return current;
    }
}
