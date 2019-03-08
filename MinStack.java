import java.util.Stack;

public class MinStack {

    Stack<Integer> stack;
    int min;
    /** initialize your data structure here. */

    public MinStack() {
        stack = new Stack<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        if(x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        if(stack.isEmpty()) {
            return;
        }
        if(stack.pop() == min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(3);
        stack.push(1);
        stack.push(2);
        stack.getMin();
    }
}
