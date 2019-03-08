package Amazon;

import java.util.Deque;
import java.util.LinkedList;

public class LargestRectangle {
    public static int largestRectangleArea(int[] heights) {
        if(heights == null || heights.length == 0) {
            return 0;
        }

        Deque<Integer> stack = new LinkedList<>();

        int ans = 0;
        int i = 0;
        for(; i < heights.length; i++) {
            while(!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int right = i;
                int minHeight = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                ans = Math.max(ans, heights[minHeight] * (right - left - 1));
            }
            stack.push(i);
        }

        while(!stack.isEmpty()) {
            int right = i;
            int minHeight = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            ans = Math.max(ans, heights[minHeight] * (right - left - 1));
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] height = {2,1,2};
        System.out.println(largestRectangleArea(height));
    }
}
