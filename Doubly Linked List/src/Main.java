public class Main {
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList(1);
        list.append(2);
        list.append(3);
        list.append(2);
        list.append(6);
        list.append(7);
        System.out.println(list.isPalindrome());
        list.swapPairs();
        list.printList();
    }
}