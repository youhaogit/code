package Amazon;

import java.util.HashMap;
import java.util.Map;

public class MinWindowSubstring {

    public static String minWindow(String s, String t) {
        int[] map = new int[128];
        for(Character c: t.toCharArray()) {
            map[c]++;
        }

        int left = 0, right = 0, length = Integer.MAX_VALUE, head = 0;
        int count = 0;
        while(right < s.length()) {
            if(map[s.charAt(right)] > 0) {
                count++;
            }
            map[s.charAt(right)]--;
            right++;

            while(count == t.length()) {
                if(right - left < length) {
                    length = right - left;
                    head = left;
                }

                if(map[s.charAt(left)] == 0) {
                    count--;
                }
                map[s.charAt(left)]++;
                left++;
            }
        }

        return length == Integer.MAX_VALUE ? "" : s.substring(head, head + length);
    }


    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));
    }
}
