import java.util.ArrayList;
import java.util.List;

public class Heap {
    private static List<Integer> heap;

    public Heap() {
        heap = new ArrayList<>();
    }

    public List<Integer> getHeap() {
        return new ArrayList<>(heap);
    }

    public static int leftChild(int index) {
        return 2 * index + 1;
    }

    public static int rightChild(int index) {
        return 2 * index + 2;
    }

    public int parent(int index) {
        return (index - 1) / 2;
    }

    public static void swap(int index1, int index2) {
        int temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    public void insert(int value) {
        heap.add(value);
        int current = heap.size() - 1;
        while (current > 0 && heap.get(current) > heap.get(parent(current))) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public Integer remove() {
        if(heap.isEmpty()) return null;
        if(heap.size() == 1) return heap.removeFirst();
        int maxValue = heap.getFirst();

        int last = heap.removeLast();
        heap.set(0, last);

        sinkDown(0);
        return maxValue;
    }

    public static void sinkDown(int index) {
        int maxIndex = index;

        while (true) {
            int leftIndex = leftChild(maxIndex);
            int rightIndex = rightChild(maxIndex);

            if (leftIndex < heap.size() && heap.get(leftIndex) > heap.get(maxIndex)) {
                maxIndex = leftIndex;
            }

            if (rightIndex < heap.size() && heap.get(rightIndex) > heap.get(maxIndex)) {
                maxIndex = rightIndex;
            }

            if (maxIndex != index) {
                swap(index, maxIndex);
                index = maxIndex;
            } else {
                return;
            }
        }

    }
}
