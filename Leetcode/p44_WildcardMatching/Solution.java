package Leetcode.p44_WildcardMatching;

public class Solution {

//    Given an input string (s) and a pattern (p), implement wildcard pattern
//    matching with support for '?' and '*'.
//
//            '?' Matches any single character.
//            '*' Matches any sequence of characters (including the empty sequence).
//    The matching should cover the entire input string (not partial).
//
//    Note:
//
//    s could be empty and contains only lowercase letters a-z.
//    p could be empty and contains only lowercase letters a-z, and characters like ? or *.

    public boolean isMatch(String s, String p) {
        int s_pos = 0;
        int p_pos = 0;
        int match = 0;
        int star = -1;

        while(s_pos < s.length()) {
            if(p_pos < p.length() && (s.charAt(s_pos) == p.charAt(p_pos) || p.charAt(p_pos) == '?')) {
                s_pos++;
                p_pos++;
            }else if(p_pos < p.length() && p.charAt(p_pos) == '*') {
                star = p_pos;
                match = s_pos;
                p_pos++;
            }else if(star != -1) {
                p_pos = star + 1;
                match++;
                s_pos = match;
            }else {
                return false;
            }
        }

        while(p_pos < p.length() && p.charAt(p_pos) == '*') {
            p_pos++;
        }

        return p_pos == p.length();
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        String S = "acdcb";
        String P = "a*c?b";
        System.out.println(s.isMatch(S, P));
    }
}