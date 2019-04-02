package Leetcode.p648_ReplaceWords;

import java.util.*;

public class Solution {

    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        String word;
    }

    class Trie {
        TrieNode root;
        Trie() {
            root = new TrieNode();
        }

        void insert(String word) {
            TrieNode cur = root;
            for(int i = 0; i < word.length(); i++) {
                char key = word.charAt(i);
                cur.children.putIfAbsent(key, new TrieNode());
                cur = cur.children.get(key);
            }
            cur.word = word;
        }
    }

    public String replaceWords(List<String> dict, String sentence) {
        if(sentence == null || dict == null) {
            return sentence;
        }

        Trie trie = new Trie();
        for(String word: dict) {
            trie.insert(word);
        }

        StringBuilder sb = new StringBuilder();
        String[] words = sentence.split(" ");


        for(String word: words) {
            TrieNode cur = trie.root;
            for(int i = 0; i < word.length(); i++) {
                char key = word.charAt(i);
                // meet the shortest prefix
                if(cur.children.get(key) == null || cur.word != null) {
                    break;
                }
                cur = cur.children.get(key);
            }
            sb.append(cur.word == null ? word : cur.word);
            sb.append(" ");
        }

        return sb.toString().trim();

    }


    public static void main(String[] args) {
        Solution s = new Solution();
        List<String> dict = new ArrayList<>(Arrays.asList(new String[]{"a", "aa", "aaa", "aaaa"}));
        String sentence = "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa";
        System.out.println(s.replaceWords(dict, sentence));
    }
}