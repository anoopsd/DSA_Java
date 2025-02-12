public class Main {
    public static void main(String[] args) {
//        Stack stack = new Stack(2);
//        stack.push(1);
//        stack.push(3);
//        stack.pop();
//        stack.printStack();
//
//        StackList<Integer> stackList = new StackList<>();
//        stackList.push(1);
//        stackList.push(2);
//        stackList.push(3);
//        stackList.pop();
//        stackList.printStack();
        StackList<Integer> stack = new StackList<>();
        stack.push(4);
        stack.push(2);
        stack.push(6);
        stack.push(9);
        stack.push(1);
        stack.push(6);
//        stack.printStack();

        sortStack(stack);

//        reverse("hello");
//        System.out.println(isBalancedParentheses("(()"));
    }

    // Interview question 1 -> Reverse a string.
    // Takes single parameter string. returns a new string.
    public static void reverse(String word) {
        StringBuilder builder = new StringBuilder();
        StackList<Character> stack = new StackList<>();
        for (char c : word.toCharArray()) {
            stack.push(c);
        }
        for (int i = 0; i < word.length(); i++) {
            Character c = stack.pop();
            builder.append(c);
        }
        System.out.println(builder.toString());
    }

    // Inteview question 2 -> Parentheses Balanced.
    public static Boolean isBalancedParentheses(String s) {
        StackList<Character> stack = new StackList<>();
        Boolean balanced = false;
        for (char c: s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.size() == 0 || stack.pop() != '(') {
                    return false;
                }
            }
        }
        if (stack.size() == 0) {
            balanced = true;
        }
        return balanced;
    }

    // Inteview question 3 -> Sort the stack.
    public static void sortStack(StackList<Integer> stack) {
        StackList<Integer> sortList = new StackList<>();
        while(!stack.isEmpty()) {
            Integer temp = stack.pop();
            while(!sortList.isEmpty() && temp > sortList.peek()) {
                stack.push(sortList.pop());
            }
            sortList.push(temp);
        }
        sortList.printStack();
    }
}