package Leetcode.p830_PositionsofLargeGroups;

import java.util.*;

public class Solution {

    public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> result = new ArrayList<>();
        if(S == null || S.length() == 0) {
            return result;
        }

        int left = 0;
        int right = 0;
        while(right < S.length()) {
            int count = 1;
            while(right + 1 < S.length() && S.charAt(right) == S.charAt(right + 1)) {
                right++;
                count++;
            }

            if(count >= 3) {
                List<Integer> sub = new ArrayList<>();
                sub.add(left);
                sub.add(right);
                result.add(sub);
            }

            right++;
            left = right;
        }

        return result;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        Set<Character> set = new HashSet<>(Arrays.asList('+', '-', '(', ')', ' '));

    }
}
