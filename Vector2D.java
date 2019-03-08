import java.util.*;

public class Vector2D implements Iterator<Integer> {

    Deque<Integer> stack;
    public Vector2D(List<List<Integer>> vec2d) {
        stack = new LinkedList<>();
        int size = vec2d.size();
        for(int i = size - 1; i >= 0; i--) {
            List<Integer> subset = vec2d.get(i);
            for(int j = subset.size() - 1; j >= 0; j--) {
                stack.push(subset.get(j));
            }
        }
    }

    @Override
    public Integer next() {
        int peek = stack.peek();
        stack.pop();
        return peek;
    }

    @Override
    public boolean hasNext() {
        return stack.isEmpty();
    }

}
