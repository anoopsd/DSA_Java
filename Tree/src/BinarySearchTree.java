public class BinarySearchTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public boolean insert(int data) {
        Node newNode = new Node(data);
        if (root == null) {
            root = newNode;
            return true;
        }
        Node temp = root;
        while (true) {
            if (newNode.value == temp.value) return false;
            if (newNode.value > temp.value) {
                if (temp.right == null) {
                    temp.right = newNode;
                    return true;
                }
                temp = temp.right;
            } else {
                if (temp.left == null) {
                    temp.left = newNode;
                    return true;
                }
                temp = temp.left;
            }
        }
    }

    public boolean contains(int data) {
        if (root == null) return false;
        Node temp = root;
        while (temp != null) {
            if  (temp.value > data) {
                temp = temp.left;
            } else if (temp.value < data) {
                temp = temp.right;
            } else {
                return true;
            }
        }
        return false;
    }
}
