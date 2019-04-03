package Leetcode.p642_Design_Search_Autocomplete_System;

import sun.text.normalizer.Trie;

import java.util.*;

public class Solution {

//    Example:
//    Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
//    The system have already tracked down the following sentences and their corresponding times:
//        "i love you" : 5 times
//        "island" : 3 times
//        "ironman" : 2 times
//        "i love leetcode" : 2 times
//    Now, the user begins another search:
//
//    Operation: input('i')
//    Output: ["i love you", "island","i love leetcode"]
//    Explanation:
//    There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.
//
//            Operation: input(' ')
//    Output: ["i love you","i love leetcode"]
//    Explanation:
//    There are only two sentences that have prefix "i ".
//
//    Operation: input('a')
//    Output: []
//    Explanation:
//    There are no sentences that have prefix "i a".
//
//    Operation: input('#')
//    Output: []
//    Explanation:
//    The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.

    static class AutocompleteSystem {

        class TrieNode {
            Map<Character, TrieNode> children;
            Map<String, Integer> freqMap;
            boolean isWord;

            public TrieNode() {
                children = new HashMap<>();
                freqMap = new HashMap<>();
                isWord = false;
            }
        }

        class Trie {
            TrieNode root;

            public Trie() {
                root = new TrieNode();
            }
        }

        class Record {
            String word;
            Integer count;

            Record(String w, Integer c) {
                this.count = c;
                this.word = w;
            }
        }


        private void insert(Trie trie, String word, int freq) {
            TrieNode cur = trie.root;
            for(int i = 0; i < word.length(); i++) {
                char key = word.charAt(i);
                if(cur.children.get(key) == null) {
                    cur.children.put(key, new TrieNode());
                }
                cur = cur.children.get(key);

                // update frequency value
                cur.freqMap.put(word, cur.freqMap.getOrDefault(word, 0) + freq);
            }
            cur.isWord = true;


        }


        Trie trie;
        String prefix;
        public AutocompleteSystem(String[] sentences, int[] times) {
            trie = new Trie();
            prefix = "";

            for(int i = 0; i < sentences.length; i++) {
                String sentence = sentences[i];
                int freq = times[i];
                insert(trie, sentence, freq);
            }
        }

        public List<String> input(char c) {
            if(c == '#') {
                insert(trie, prefix, 1);

                // reset prefix for new word insertion
                prefix = "";
                return new ArrayList<>();
            }

            prefix += c;
            TrieNode cur = trie.root;

            // find the prefix node sofar
            for(int i = 0; i < prefix.length(); i++) {
                char key = prefix.charAt(i);
                if(cur.children.get(key) == null) {
                    return new ArrayList<>();
                }
                cur = cur.children.get(key);
            }

            // check the freqMap of current trienode and fetch the result
            PriorityQueue<Record> heap = new PriorityQueue<>((a, b) ->(a.count == b.count ? a.word.compareTo(b.word) : b.count - a.count));
            for(String word: cur.freqMap.keySet()) {
                heap.offer(new Record(word, cur.freqMap.get(word)));
            }

            List<String> result = new ArrayList<>();
            while(!heap.isEmpty()) {
                result.add(heap.poll().word);
                if(result.size() == 3) {
                    break;
                }
            }

            return result;
        }
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        String[] sentences = {"i love you","island","i love leetcode", "ironman"};
        int[] times = {5,3,2,2};
        AutocompleteSystem ac = new AutocompleteSystem(sentences, times);

        System.out.println(ac.input('i'));
        System.out.println(ac.input(' '));
        System.out.println(ac.input('a'));
        System.out.println(ac.input('#'));

    }
}