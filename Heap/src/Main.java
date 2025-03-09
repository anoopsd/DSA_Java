import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Heap heap = new Heap();
        heap.insert(95);
        heap.insert(75);
        heap.insert(80);
        heap.insert(55);
        heap.insert(60);
        heap.insert(50);
        heap.insert(65);
        heap.remove();

        System.out.println(heap.getHeap());
        int[] nums = {7, 10, 4, 3, 20, 15};
        int k = 3;
        System.out.println(findKthSmallest(nums, k));

        int[] arr = {1, 5, 2, 9, 3, 6, 8};
        System.out.println(streamMax(arr));

    }

    public static int findKthSmallest(int[] nums, int k) {
        if (nums.length == 1) return -1;
        Heap heap = new Heap();
        for (int s: nums) {
            heap.insert(s);
            if (heap.getHeap().size() > k) {
                heap.remove();
            }
        }
        return heap.getHeap().getFirst();
    }

    public static List<Integer> streamMax(int[] nums) {
        int max = Integer.MIN_VALUE;
        List<Integer> res = new ArrayList<>();
        for (int c: nums) {
            if (c > max) {
                max = c;
            }
            res.add(max);
        }
        return res;
    }

    public static List<Integer> streamMaxHeap(int[] nums) {
        Heap maxHeap = new Heap();
        List<Integer> res = new ArrayList<>();
        for (int c: nums) {
            maxHeap.insert(c);
            res.add(maxHeap.getHeap().getFirst());
        }
        return res;
    }
}