public class Main {
    public static void main(String[] args) {
        LinkedList list = new LinkedList(1);
        list.append(0);
        list.append(1);
        list.append(1);
        list.append(1);
        System.out.println(list.binaryToDecimal());
        list.printList();
    }
}