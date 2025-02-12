import java.util.ArrayList;

public class StackList<T> {
    private ArrayList<T> stack = new ArrayList<>();

    public ArrayList<T> getStack() {
        return stack;
    }

    public void printStack() {
        for (int i = 0; i < stack.size(); i++) {
            System.out.println(stack.get(i));
            System.out.println("↓");
        }
    }

    public void push(T t) {
        stack.add(t);
    }

    public T pop() {
        return stack.removeLast();
    }

    public int size() {
        return stack.size();
    }

    public Boolean isEmpty() {
        return stack.isEmpty();
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        } else {
            return stack.getLast();
        }
    }

}
