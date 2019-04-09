package Leetcode.p3_LongestSubstringWithoutRepeatingCharacters;

import java.util.*;

public class Solution {

    public int lengthOfLongestSubstring(String s) {
        // general idea is to use silding window with set
        if(s == null || s.length() == 0) {
            return 0;
        }

        Set<Character> set = new HashSet<>();
        int ans = 0;
        int i = 0;
        int j = 0;

        while(j < s.length()) {
            char c = s.charAt(j);
            if(!set.contains(c)) {
                j++;
                set.add(c);
                ans = Math.max(ans, j - i);
            }else {
                set.remove(s.charAt(i++));
            }
        }

        return ans;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "bbbbb";
        System.out.println(s.lengthOfLongestSubstring(str));

    }
}