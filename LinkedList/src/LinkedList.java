import java.util.HashSet;
import java.util.Set;

public class LinkedList {
    private Node head;
    private Node tail;
    private int length;

    public LinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.value);
            System.out.print("->");
            temp = temp.next;
        }
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
            length = 1;
        } else {
            tail.next = newNode;
            tail = newNode;
            length++;
        }
    }

    public Node removeLast() {
        if (head == null) {
            System.out.println("List is empty");
            return null;
        } else {
            Node prev = head;
            Node after = head;
            while (after.next != null) {
                prev = after;
                after = after.next;
            }
            tail = prev;
            prev.next = null;
            length--;
            if (length == 0) {
                head = null;
                tail = null;
            }
            return after;
        }
    }

    public Node removeFirst() {
        if (head == null) {
            System.out.println("List is empty");
            return null;
        }
        Node temp = head;
        head = head.next;
        temp.next = null;
        length--;
        if (length == 0) {
            tail = null;
        }
        return temp;
    }

    public boolean insert(int index, int value) {
        if (index == 0) {
            prepend(value);
            return true;
        }
        if (index == length) {
            append(value);
            return true;
        }
        Node newNode = new Node(value);
        Node temp = get(index - 1);
        newNode.next = temp.next;
        temp.next = newNode;
        return true;
    }

    public Node remove(int index) {
        if (index < 0 || index >= length) return null;
        if (index == 0) return removeFirst();
        if (index == length - 1) return removeLast();
        Node prev = get(index - 1);
        Node temp = prev.next;
        prev.next = temp.next;
        temp.next = null;
        return temp;
    }

    public void prepend(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        length++;
    }

    public Node get(int index) {
        if (index < 0 || index >= length) {
            return null;
        }
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
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

    public void reverse() {
        if (head == null) return;
        Node temp = head;
        head = tail;
        tail = temp;
        Node prev= null;
        Node after = temp.next;
        while (temp != null) {
            after = temp.next;
            temp.next = prev;
            prev = temp;
            temp = after;
        }
    }

    public void getHead() {
        System.out.println("Head: " + head.value);
    }

    public void getTail() {
        System.out.println("Tail: " + tail.value);
    }

    public void getLength() {
        System.out.println("Length: " + length);
    }

    // Interview Question->1 -> Find the Middle Node.
    // Using Floyd's Tortoise and Hare algorithm.

    public Node findMiddleNode() {
        if (head == null) return null;
        if (head.next == null) return head;
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast.next != null) {
            return slow.next;
        }
        return slow;
    }

    // Interview Question->2 -> Check whether the LinkedList has a loop.
    // Using Floyd's Tortoise and Hare algorithm.
    public boolean hasLoop() {
        if (head == null || head.next == null) return false;
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    // Interview Question->3 -> Find the kth node from the end of a linked list.
    // Using two pointer algorithm.
    public Node findKthFromEnd(int k) {
        Node first = head;
        Node second = head;
        int count = 1;
        while (first != null) {
            first = first.next;
            if (count >= k) {
                second = second.next;
            }
            count++;
        }
        if (count < k) return null;
        return second;
    }

    // Interview Question->4 -> Rearrange the order of the linkedList such that the nodes
    // with a value less than x should come before all the nodes with a value greater than or equal to x.
    public void  partitionList(int x) {
        if (head == null) return;
        Node before = null;
        Node after = null;
        Node before_head = null;
        Node after_head = null;
        Node current = head;
        while (current != null) {
            Node next = current.next;
            current.next = null;
            if (current.value < x) {
                if(before == null) {
                    before = current;
                    before_head = current;
                } else {
                    before.next = current;
                    before = before.next;
                }
            } else {
                if(after == null) {
                    after = current;
                    after_head = current;
                } else {
                    after.next = current;
                    after = after.next;
                }
            }
            current = next;
        }
        if (before_head == null) {
            head = after_head;
        } else {
            head = before_head;
            before.next = after_head;
        }
    }

    // Interview Question->5 -> Remove all duplicate values from the LinkedList.
    //Constraints -> should not create new list , preserve the natural order.
    //Solution 1: O(n2). using two loops
    public void removeDuplicates() {
        if (head == null) return;
        Node slow = head;
        while (slow != null) {
            Node fast = slow;
            while(fast.next != null) {
                if (fast.next.value == slow.value) {
                    fast.next = fast.next.next;
                    length -= 1;
                } else {
                    fast = fast.next;
                }
            }
            slow = slow.next;
        }

        // Solution 2: Using HashSet. Complexity O(n).
        if (head == null) return;
        Set<Integer> lookup = new HashSet<>();
        Node current = head;
        lookup.add(current.value);
        while (current.next != null) {
            if (lookup.contains(current.next.value)) {
                current.next = current.next.next;
                length -= 1;
            } else {
                lookup.add(current.next.value);
                current = current.next;
            }
        }
    }
    // Interview Question->6 -> Binary to decimal conversion.
    //Solution: using binary left shift.

    public int binaryToDecimal() {
        int res = 0;
        if (head == null) return 0;
        Node current = head;
        while (current != null) {
            res = (res * 2) + current.value;
            current = current.next;
        }
        return res;
    }

    // Interview Question->7 -> Reverses the nodes of the list between starting and ending index.
    //Solution:
    public void reverseBetween(int m, int n) {
        if (head == null) return;
        Node dummy = new Node(0);
        dummy.next = head;
        Node prev = dummy;
        for (int i = 0; i < m; i++) {
            prev = prev.next;
        }
        Node current = prev.next;
        for (int i = 0; i < n - m; i++) {
            Node nodeToMove = current.next;
            current.next = nodeToMove.next;
            nodeToMove.next = prev.next;
            prev.next = nodeToMove;
        }
        head = dummy.next;
    }

    //Interview question-> 08 bubblesort
    public void bubbleSort() {
        if (length < 2) return;
        Node sortedUntill = null;
        while (sortedUntill != head.next) {
            Node current = head;
            while (current.next != sortedUntill) {
                if(current.value > current.next.value) {
                    int temp = current.value;
                    current.value = current.next.value;
                    current.next.value = temp;
                }
                current = current.next;
            }
            sortedUntill = current;
        }
    }
}
