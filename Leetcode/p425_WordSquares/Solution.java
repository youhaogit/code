package Leetcode.p425_WordSquares;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

//    Example 1:
//
//    Input:
//            ["area","lead","wall","lady","ball"]
//
//    Output:
//            [
//            [ "wall",
//            "area",
//            "lead",
//            "lady"
//            ],
//            [ "ball",
//            "area",
//            "lead",
//            "lady"
//            ]
//            ]

    // general idea is to use trie to store all the possible strings associated with a prefix,
    // then choose the correct one from the prefix

    class TrieNode {
        TrieNode[] children;
        List<String> candidates;

        TrieNode() {
            children = new TrieNode[26];
            candidates = new ArrayList<>();
        }
    }

    class Trie {
        TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        void insert(String word) {
            TrieNode cur = root;
            for(int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                if(cur.children[idx] == null) {
                    cur.children[idx] = new TrieNode();
                }
                cur = cur.children[idx];
                cur.candidates.add(word);
            }
        }

        List<String> startsWith(String prefix) {
           List<String> res = new ArrayList<>();
           TrieNode cur = root;
           for(int i = 0; i < prefix.length(); i++) {
               int idx = prefix.charAt(i) - 'a';
               if(cur.children[idx] == null) {
                   return res;
               }
               cur = cur.children[idx];
           }

           res.addAll(cur.candidates);
           return res;
        }
    }


    public List<List<String>> wordSquares(String[] words) {

        // each word can be used more than once
        List<List<String>> res = new ArrayList<>();
        if(words == null || words.length == 0) {
            return res;
        }

        // init trie
        Trie trie = new Trie();
        for(String word: words) {
            trie.insert(word);
        }

        // back track to find the result
        for(String word: words) {
            int len = word.length();
            List<String> subset = new ArrayList<>();
            subset.add(word);

            backTrack(words, trie, res, subset, len);
        }

        return res;
    }

    private void backTrack(String[] words, Trie trie, List<List<String>> res, List<String> subset, int len) {
        if(subset.size() == len) {
            res.add(new ArrayList<>(subset));
            return;
        }

        StringBuilder prefix = new StringBuilder();
        int idx = subset.size();
        for(String s: subset) {
            prefix.append(s.charAt(idx));
        }

        List<String> candidates = trie.startsWith(prefix.toString());
        for(String candidate: candidates) {
            subset.add(candidate);
            backTrack(words, trie, res, subset, len);
            subset.remove(subset.size() - 1);
        }
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        String[] words = {"momy", "oooo", "yoyo"};
        System.out.println(s.wordSquares(words));
    }
}