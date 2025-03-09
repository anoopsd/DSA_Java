public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(47);
        bst.insert(21);
        bst.insert(76);
        bst.insert(18);
        bst.insert(27);
        bst.insert(82);
        bst.insert(52);

        System.out.println(bst.rContains(1));
        System.out.println(bst.rContains(6));

        System.out.println(bst.DFSInOrder());

        int[] nums = {1,2,3,4,5,6};
        bst.sortToBST(nums, 0 , nums.length - 1);
    }
}