import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
    private Node root;
    private int count = 0;
    private int result = -1;

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

    public boolean rContains(int value) {
        return rContains(root, value);
    }

    public void rInsert(int value) {
        if (root == null) root = new Node(value);
        rInsert(root, value);
    }

    public void deleteNode(int value) {
        deleteNode(root, value);
    }

    private boolean rContains(Node currentNode, int value) {
        if (currentNode == null) return false;
        if (currentNode.value == value) return true;
        if (currentNode.value > value) {
            return rContains(currentNode.left, value);
        } else {
            return rContains(currentNode.right, value);
        }
    }

    private Node rInsert(Node currentNode, int value) {
        if (currentNode == null) return new Node(value);
        if (currentNode.value > value) {
            currentNode.left = rInsert(currentNode.left, value);
        } else if (currentNode.value < value) {
            currentNode.right = rInsert(currentNode.right, value);
        }
        return currentNode;
    }

    private Node deleteNode(Node currentNode, int value) {
        if (currentNode == null) return null;
        if(value < currentNode.value) {
            currentNode.left = deleteNode(currentNode.left, value);
        }else if (value > currentNode.value) {
            currentNode.right = deleteNode(currentNode.right, value);
        } else {
            if (currentNode.left == null && currentNode.right == null) {
                return null;
            } else if (currentNode.left == null) {
                currentNode = currentNode.right;
            } else if (currentNode.right == null) {
                currentNode = currentNode.left;
            } else {
                int subTreeMin = minValue(currentNode.right);
                currentNode.value = subTreeMin;
                currentNode.right = deleteNode(currentNode.right, subTreeMin);
            }
        }
        return currentNode;
    }

    private int minValue(Node currentNode) {
        while(currentNode.left != null) {
            currentNode = currentNode.left;
        }
        return currentNode.value;
    }

    private Node sortedArrayToBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;

        Node root = new Node(nums[mid]);

        root.left = sortedArrayToBST(nums, left, mid -1);
        root.right = sortedArrayToBST(nums, mid + 1, right);

        return root;
    }

    public void sortToBST(int[] nums, int left, int right) {
        Node root = sortedArrayToBST(nums, left, right);
        System.out.println(root.value);
    }

    public ArrayList<Integer> BFS() {
        Node currentNode = root;
        Queue<Node> q = new LinkedList<>();
        ArrayList<Integer> res = new ArrayList<>();

        q.add(currentNode);

        while (!q.isEmpty()) {
            Node curr = q.poll();
            res.add(curr.value);
            if (curr.left != null) {
                q.add(curr.left);
            }

            if (curr.right != null) {
                q.add(curr.right);
            }
        }
        return res;
    }

    public ArrayList<Integer> DFSPreOrder() {
        ArrayList<Integer> results = new ArrayList<>();
        DFSrPreOrder(root, results);
        return results;
    }

    private void DFSrPreOrder(Node current, ArrayList<Integer> res) {
        if (current == null) {
            return;
        }
        res.add(current.value);
        DFSrPreOrder(current.left, res);
        DFSrPreOrder(current.right, res);
    }

    public ArrayList<Integer> DFSPostOrder() {
        ArrayList<Integer> results = new ArrayList<>();
        DFSrPostOrder(root, results);
        return results;
    }
    private void DFSrPostOrder(Node current, ArrayList<Integer> res) {
        if (current == null) {
            return;
        }
        DFSrPostOrder(current.left, res);
        DFSrPostOrder(current.right, res);
        res.add(current.value);
    }

    public ArrayList<Integer> DFSInOrder() {
        ArrayList<Integer> results = new ArrayList<Integer>();
        DFSrInOrder(root, results);
        return results;
    }

    private void DFSrInOrder(Node current, ArrayList<Integer> res) {
        if (current == null) {
            return;
        }
        DFSrInOrder(current.left, res);
        res.add(current.value);
        DFSrInOrder(current.right, res);
    }

    public boolean isValidBST() {
        ArrayList<Integer> traversed = new ArrayList<>();
        DFSrInOrder(root, traversed);

        for(int i = 1; i < traversed.size(); i++) {
            if (traversed.get(i) < traversed.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public int kthSmallest(int k) {
        inOrderTraversal(root, k);
        return result;
    }

    private void inOrderTraversal(Node current, int k) {
        if (current == null) {
            return;
        }
        inOrderTraversal(current.left, k);
        count++;
        if (count == k) {
            result = current.value;
            return;
        }
        inOrderTraversal(current.right, k);
    }
}
