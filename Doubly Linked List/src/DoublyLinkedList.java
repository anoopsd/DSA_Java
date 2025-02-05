public class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int length;
    public DoublyLinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        length++;
    }

    public Node removeLast() {
        if (head == null) return null;
        Node temp = tail;
        if (length == 1) {
            tail = null;
            head = null;
        } else {
            tail = tail.prev;
            tail.next = null;
            temp.prev = null;
        }
        length--;
        return temp;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.value);
            System.out.print("->");
            temp = temp.next;
        }
    }

    public void prepend(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        length++;
    }

    public Node removeFirst() {
        Node temp = head;
        if (head == null) return null;
        if (length == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            temp.next = null;
            head.prev = null;
        }
        length--;
        return temp;
    }

    public Node get(int index) {
        if (index < 0 || index >= length) return null;
        Node temp = head;
        if (index < length/2) {
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
        } else {
            temp = tail;
            for (int i = 1; i < length - index; i++ ) {
               temp = temp.prev;
            }
        }
        return temp;
    }

    public boolean set(int index, int value) {
        Node temp = get(index);
        if (temp != null) {
            temp.value = value;
            return true;
        }
        return false;
    }

    public boolean insert(int index, int value) {
        if (index < 0 || index > length) return false;
        Node newNode = new Node(value);
        if (index == 0 ) {
            prepend(value);
            return true;
        }
        if (index == length) {
            append(value);
            return true;
        }
        Node before = get(index - 1);
        Node after = before.next;
        newNode.next = after;
        newNode.prev = before;
        before.next = newNode;
        after.prev = newNode;
        length++;
        return true;
    }

    public Node remove(int index) {
        if(index< 0 || index >= length) return null;
        if (index == 0) return removeFirst();
        if (index == length - 1) return removeLast();
        Node temp = get(index);
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
        temp.next = null;
        temp.prev = null;
        length--;
        return temp;
    }

    // Interview Question->1 -> Swap first and last node values of DLL.
    // Just swap the values no need of pointer swapping.
    public void swapFirstLast() {
        if (length < 2) return;
        Node temp = new Node(0);
        temp.value  = head.value;
        head.value = tail.value;
        tail.value = temp.value;
    }

    // Interview Question->2 -> Reverse the DLL.
    // Do not swap the values. Manipulate the pointers.
    public void reverse() {
        if (length < 2) return;
        Node temp = head;
        Node prev = null;
        while (temp != null) {
            prev = temp.prev;
            temp.prev = temp.next;
            temp.next = prev;
            temp = temp.prev;
        }
        temp = head;
        head = tail;
        tail = temp;
    }

    // Interview Question->3 -> Palindrome checker.
    public boolean isPalindrome() {
        if (length == 1) return true;
        Node first = head;
        Node second = tail;
        while (first != second) {
            if (first.value != second.value) {
                return false;
            }
            first = first.next;
            second = second.prev;
        }
        return true;
    }

    // Interview Question->4 -> Swap Nodes in pairs.
    // Solve without modifying the values.
    public void swapPairs() {
        if (length < 2) return;
        Node dummy = new Node(0);
        dummy.next = head;
        Node previousNode = dummy;
        while( head != null && head.next != null) {
            Node firstNode = head;
            Node secondNode = head.next;
            previousNode.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;
            secondNode.prev = previousNode;
            firstNode.prev = secondNode;
            if (firstNode.next != null) {
                firstNode.next.prev = firstNode;
            }
            head = firstNode.next;
            previousNode = firstNode;
        }
        head = dummy.next;
        if (head != null) head.prev = null;
    }

    public void getHead() {
        System.out.println("Head : " + head.value);
    }

    public void getTail() {
        System.out.println("Tail : " + tail.value);
    }

    public void getLength() {
        System.out.println("Length : " + length);
    }
}
