package Amazon;

import java.util.Deque;
import java.util.LinkedList;

public class BaseBallGame {

    public static int calPoints(String[] ops) {
        if(ops == null || ops.length == 0) {
            return 0;
        }

        int sum = 0;
        Deque<Integer> stack = new LinkedList<>();
        for(String c: ops) {
            if(c.equals("C")) {
                int last = stack.pop();
                sum -= last;
            }else if(c.equals("D")) {
                int val = 2 * stack.peek();
                stack.push(val);
                sum += val;
            }else if(c.equals("+")) {
                int validOne = stack.pop();
                int validTwo = stack.pop();
                int val = validOne + validTwo;
                sum += val;

                stack.push(validTwo);
                stack.push(validOne);
                stack.push(val);
            }else {
                int val = Integer.parseInt(c);
                stack.push(val);
                sum += val;
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        String[] ops = {"5","-2","4","C","D","9","+","+"};
        System.out.println(calPoints(ops));
    }
}
