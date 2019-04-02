package Leetcode.p10_RegularExpressionMatching;

public class Solution {

//    Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
//
//            '.' Matches any single character.
//            '*' Matches zero or more of the preceding element.
//    The matching should cover the entire input string (not partial).

    public boolean isMatch(String s, String p) {
       if(s == null && p == null) {
           return true;
       }else if(s != null && p == null) {
           return false;
       }

       // init dp[][]
       boolean dp[][] = new boolean[s.length() + 1][p.length() + 1];
       dp[0][0] = true;

       for(int j = 0; j < p.length(); j++) {
           if(p.charAt(j) == '*' && dp[0][j - 1]) {
               dp[0][j + 1] = true;
           }
       }

       for(int i = 0; i < s.length(); i++) {
           for(int j = 0; j < p.length(); j++) {
               if(p.charAt(j) == '.' || s.charAt(i) == p.charAt(j)) {
                   dp[i + 1][j + 1] = dp[i][j];
               }
               if(p.charAt(j) == '*') {
                   // eg: ab -> .*
                   if(p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {
                       dp[i + 1][j + 1] = dp[i + 1][j - 1];
                   }else {
                       // eg: aa, a* count as single a, multiple a, or empty
                       dp[i + 1][j + 1] = dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1];
                   }
               }
           }
       }

       return dp[s.length()][p.length()];

    }


    public static void main(String[] args) {
        Solution s = new Solution();
    }
}