package Leetcode.p211_AddandSearchWord_Datastructuredesign;

import util.TreeNode;

public class Solution {

//    Design a data structure that supports the following two operations:
//
//    void addWord(word)
//    bool search(word)
//    search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
//
//    Example:
//
//    addWord("bad")
//    addWord("dad")
//    addWord("mad")
//    search("pad") -> false
//    search("bad") -> true
//    search(".ad") -> true
//    search("b..") -> true

    static class WordDictionary {

        class TrieNode {
            TrieNode[] children;
            boolean isWord;

            TrieNode() {
                children = new TrieNode[26];
                isWord = false;
            }
        }

        /** Initialize your data structure here. */
        TrieNode root;
        public WordDictionary() {
            root = new TrieNode();
        }

        /** Adds a word into the data structure. */
        public void addWord(String word) {
            TrieNode cur = root;
            for(int i = 0; i < word.length(); i++) {
                int key = word.charAt(i) - 'a';
                if(cur.children[key] == null) {
                    cur.children[key] = new TrieNode();
                }
                cur = cur.children[key];
            }
            cur.isWord = true;
        }

        /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
        public boolean search(String word) {
            return searchHelper(root, word);
        }

        private boolean searchHelper(TrieNode root, String word) {
            TrieNode cur = root;
            for(int i = 0; i < word.length(); i++) {
                if(word.charAt(i) == '.') {

                   for(TrieNode child: cur.children) {
                       if(child != null) {
                           if(searchHelper(child, word.substring(i + 1))) {
                               return true;
                           }
                       }
                   }
                   return false;
                }

                int key = word.charAt(i) - 'a';
                if(cur.children[key] == null) {
                    return false;
                }
                cur = cur.children[key];
            }

            return cur.isWord;
        }
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        WordDictionary wd = new WordDictionary();

        wd.addWord("bad");
        wd.addWord("dad");
        wd.addWord("mad");
        wd.addWord("pad");

        System.out.println(wd.search("bad"));
        System.out.println(wd.search(".ad"));
        System.out.println(wd.search("b.."));
    }
}