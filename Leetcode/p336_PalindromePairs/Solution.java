package Leetcode.p336_PalindromePairs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

//    Given a list of unique words, find all pairs of distinct indices (i, j) in the given list,
//    so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
//
//            Example 1:
//
//    Input: ["abcd","dcba","lls","s","sssll"]
//    Output: [[0,1],[1,0],[3,2],[2,4]]
//    Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        if(words == null || words.length == 0) {
            return result;
        }

        for(int i = 0; i < words.length; i++) {
            for(int j = 0; j < words.length; j++) {
                if(j == i) {
                    continue;
                }
                String candidate = words[i] + words[j];
                if(isPalindrome(candidate)) {
                    List<Integer> ans = new ArrayList<>();
                    ans.add(i);
                    ans.add(j);
                    result.add(ans);
                }
            }
        }

        return result;
    }

    private boolean isPalindrome(String word) {
        int i = 0;
        int j = word.length() - 1;
        while(i < j) {
            if(word.charAt(i) != word.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String[] words = {"abcd","dcba","lls","s","sssll"};
        System.out.println(s.palindromePairs(words));
    }
}