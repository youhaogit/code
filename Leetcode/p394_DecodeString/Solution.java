package Leetcode.p394_DecodeString;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

//    s = "3[a]2[bc]", return "aaabcbc".
//    s = "3[a2[c]]", return "accaccacc".
//    s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

    public String decodeString(String s) {
        if(s == null || s.length() == 0) {
            return "";
        }

        Queue<Character> queue = new LinkedList<>();
        for(char c: s.toCharArray()) {
            queue.offer(c);
        }
        return helper(queue);
    }

    private String helper(Queue<Character> queue) {
        StringBuilder sb = new StringBuilder();

        int num = 0;
        while(!queue.isEmpty()) {
            char cur = queue.poll();
            if(Character.isDigit(cur)) {
                num = num * 10 + cur - '0';
            }else if(Character.isLetter(cur)) {
                sb.append(cur);
            }else if(cur == '[') {
                String str = helper(queue);
                for(int i = 0; i < num; i++) {
                    sb.append(str);
                }
                num = 0;
            }else if(cur == ']') {
                break;
            }
        }

        return sb.toString();

    }

    public String decodeStringII(String s) {
        if(s == null || s.length() == 0) {
            return "";
        }

        Deque<String> stack = new LinkedList<>();
        Deque<Integer> nums = new LinkedList<>();

        String res = "";


        int pos = 0;
        while(pos < s.length()) {
            char cur = s.charAt(pos);
            if(Character.isDigit(cur)) {
                int num = 0;
                while(pos < s.length() && Character.isDigit(s.charAt(pos))) {
                    num = num * 10 + (s.charAt(pos) - '0');
                    pos++;
                }
                nums.push(num);

            }else if(cur == '[') {
                stack.push(res);
                res = "";
                pos++;
            }else if(cur == ']') {
                int num = nums.pop();
                StringBuilder sb = new StringBuilder(stack.pop());
                for(int i = 0; i < num; i++) {
                    sb.append(res);
                }
                res = sb.toString();
                pos++;
            }else {
                res += cur;
                pos++;
            }
        }

        return res;

    }

    public static void main(String[] args) {
        String str = "3[a]2[bc]";
        Solution s = new Solution();
        System.out.println(s.decodeStringII(str));

    }
}
