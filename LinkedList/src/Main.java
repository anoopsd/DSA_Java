public class Main {
    public static void main(String[] args) {
        LinkedList list = new LinkedList(1);
        list.append(6);
        list.append(8);
        list.append(4);
        list.append(2);
        list.bubbleSort();
        list.printList();
    }
}