public class Main {
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList(4);
        list.append(8);
        list.prepend(12);
        list.prepend(45);
        list.append(67);
        Node node = list.get(3);
        list.set(3, 9);
        System.out.println(node.value);
        list.insert(2,100);
        list.remove(2);
        list.remove(4);
        list.swapFirstLast();
        list.printList();
    }
}