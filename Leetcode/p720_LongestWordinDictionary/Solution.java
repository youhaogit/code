package Leetcode.p720_LongestWordinDictionary;

import java.util.*;

public class Solution {

    class TrieNode {
        TrieNode[] children;
        String word;
        TrieNode() {
            children = new TrieNode[26];
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
            }
            cur.word = word;
        }

        String findLongestWord() {
            String res = "";
            Queue<TrieNode> queue = new LinkedList<>();
            queue.offer(root);

            while(!queue.isEmpty()) {
                int size = queue.size();
                for(int i = 0; i < size; i++) {
                    TrieNode cur = queue.poll();
                    if(cur.word != null) {
                        res = cur.word;
                    }

                    for(int j = 25; j >= 0; j--) {
                        if(cur.children[j] != null && cur.children[j].word != null) {
                            queue.offer(cur.children[j]);
                        }
                    }
                }
            }

            return res;
        }
    }

    public String longestWord(String[] words) {
        if(words == null || words.length == 0) {
            return "";
        }

        Trie trie = new Trie();
        for(String word: words) {
            trie.insert(word);
        }

        return trie.findLongestWord();
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        String[] words = {"w","wo","wor","worl","world"};
        System.out.println(s.longestWord(words));

    }
}