package Leetcode.p224_BasicCalculator;

import java.util.Stack;

public class Solution {

    public int calculate(String s) {
        s = s.trim();
        char[] arr = s.toCharArray();

        int num = 0;
        Stack<Integer> stack = new Stack<>();
        int sign = 1;
        int result = 0;
        for(int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if(Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }else if(c == '+') {
                result += sign * num;
                sign = 1;
                num = 0;
            }else if(c =='-') {
                result += sign * num;
                sign = -1;
                num = 0;
            }else if(c == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            }else if(c == ')') {
                result += sign * num;
                num = 0;
                result *= stack.pop();
                result += stack.pop();
            }
        }

        if(num != 0) {
            result += sign * num;
        }
        return result;

    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "1+(4-5+2)-3";
        System.out.println(s.calculate(str));
    }
}
