package Leetcode.p208_ImplementTrie;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    class Trie {

        class TrieNode {
            Map<Character, TrieNode> children;
            boolean isWord;

            TrieNode() {
                children = new HashMap<>();
                isWord = false;
            }
        }

        /** Initialize your data structure here. */
        TrieNode root;
        public Trie() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TrieNode cur = root;
            for(int i = 0; i < word.length(); i++) {
                char key = word.charAt(i);
                if(!cur.children.containsKey(key)) {
                    cur.children.put(key, new TrieNode());
                }
                cur = cur.children.get(key);
            }
            cur.isWord = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TrieNode cur = root;
            for(int i = 0; i < word.length(); i++) {
                char key = word.charAt(i);
                if(!cur.children.containsKey(key)) {
                    return false;
                }
                cur = cur.children.get(key);
            }
            return cur.isWord;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {

            TrieNode cur = root;
            for(int i = 0; i < prefix.length(); i++) {
                char key = prefix.charAt(i);
                if(!cur.children.containsKey(key)) {
                    return false;
                }
                cur = cur.children.get(key);
            }
            return true;

        }
    }


    public static void main(String[] args) {
        Solution s = new Solution();
    }
}