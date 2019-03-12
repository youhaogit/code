package Leetcode.p678_ValidParenthesisString;

import java.util.Arrays;

public class Solution {

    public boolean checkValidString(String s) {
        int n = s.length();
        if(n == 0) {
            return true;
        }

        boolean[][] dp = new boolean[n][n];

        // length 1 senario
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == '*') {
                dp[i][i] = true;
            }
        }

        for(int len = 2; len <= n; len++) {
            for(int i = 0; i + len <= n; i++) {
                int j = i + len - 1;

                //1. nested situation
                if((s.charAt(i) == '(' || s.charAt(i) == '*') && (s.charAt(j) == ')' || s.charAt(j) == '*')) {
                    if(len == 2 || dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        continue;
                    }
                }

                //2. split into left and right part
                for(int k = i; k < j; k++) {
                    if(dp[i][k] && dp[k + 1][j]) {
                        dp[i][j] = true;
                        break;
                    }
                }
            }
        }

        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "((()))()(())(*()()())**(())()()()()((*()*))((*()*)";
        System.out.println(s.checkValidString(str));
    }
}
