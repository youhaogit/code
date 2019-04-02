package Leetcode.p677_MapSumPairs;

import util.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Solution {

//    Input: insert("apple", 3), Output: Null
//    Input: sum("ap"), Output: 3
//    Input: insert("app", 2), Output: Null
//    Input: sum("ap"), Output: 5

    static class MapSum {

        class TrieNode {
            Map<Character, TrieNode> children;
            int val;

            public TrieNode() {
                children = new HashMap<>();
                val = 0;
            }
        }


        /** Initialize your data structure here. */
        TrieNode root;
        Map<String, Integer> map;
        public MapSum() {
            root = new TrieNode();
            map = new HashMap<>();
        }

        public void insert(String key, int val) {

            int delta = val - map.getOrDefault(key, 0);
            map.put(key, val);

            TrieNode cur = root;
            for(int i = 0; i < key.length(); i++) {
                char k = key.charAt(i);
                if(!cur.children.containsKey(k)) {
                    cur.children.put(k, new TrieNode());
                }
                cur = cur.children.get(k);
                cur.val += delta;
            }

        }

        public int sum(String prefix) {
            TrieNode cur = root;
            for(int i = 0; i < prefix.length(); i++) {
                char k = prefix.charAt(i);
                cur = cur.children.get(k);
                if(cur == null) {
                    return 0;
                }
            }
            return cur.val;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        MapSum ms = new MapSum();
        ms.insert("apple", 3);
        ms.insert("app", 2);
        System.out.println(ms.sum("ap"));
    }
}