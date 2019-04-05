package Leetcode.p460_LFUCache;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {

    //TODO: not correct answer
    class LFUCache {

        int stamp = 1;
        class Node {
            int key;
            int val;
            int freq;
            int timeStamp;

            Node(int key, int val) {
                this.key = key;
                this.val = val;
                this.freq = 1;
                this.timeStamp = stamp;
                stamp++;
            }
        }


        PriorityQueue<Node> pq;
        Map<Integer, Node> map;
        int capacity;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();
            this.pq = new PriorityQueue<>((a, b) ->(a.freq == b.freq ? a.timeStamp - b.timeStamp : a.freq - b.freq));
        }

        public int get(int key) {
            if(!map.containsKey(key)) {
                return -1;
            }

            Node node = map.get(key);
            node.freq++;

            pq.remove(node);
            pq.add(node);

            return node.val;
        }

        public void put(int key, int value) {
            Node node = map.get(key);
            if(node == null) {
                node = new Node(key, value);
                map.put(key, node);

                if(pq.size() == capacity) {
                    pq.poll();
                }
                pq.offer(node);
                this.capacity++;
            }else {
                pq.remove(node);

                node.val = value;
                node.freq++;
                map.put(key, node);
                pq.offer(node);
            }
        }
    }


    public static void main(String[] args) {
        Solution s = new Solution();
    }
}