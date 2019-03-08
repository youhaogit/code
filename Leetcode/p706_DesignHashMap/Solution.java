package Leetcode.p706_DesignHashMap;

import java.util.Objects;

public class Solution {

    static class MyHashMap {

        /** Initialize your data structure here. */

        static class Node {
            int key;
            int val;
            Node next;

            Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }


        static final int INIT_CAPACITY = 16;
        static final float LOAD_FACTOR = 0.75f;

        Node[] table;
        int capacity;
        int size = 0;

        public MyHashMap() {
            table = new Node[INIT_CAPACITY];
            capacity = threadForSize(INIT_CAPACITY);
            size = 0;
        }

        private int hash(int key) {
            int h;
            return (h = Objects.hashCode(key)) ^ (h >>> 16);
        }

        private int threadForSize(int cap) {
            return (int)(cap * LOAD_FACTOR);
        }

        /** value will always be non-negative. */
        public void put(int key, int value) {
            int idx = hash(key);

            Node node = table[idx];
            while(node != null) {
                if(node.key == key) {
                    node.val = value;
                    return;
                }
                node = node.next;
            }

            Node newNode = new Node(key, value);
            newNode.next = node;
            table[idx] = newNode;

            return;

        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            int idx = hash(key);

            Node node = table[idx];
            while(node != null) {
                if(node.key == key) {
                    return node.val;
                }
            }

            return -1;
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            int idx = hash(key);

            if(table[idx] == null) {
                return;
            }else {
                Node dummy = new Node(-1, -1);
                dummy.next = table[idx];

                Node pre = dummy;
                Node cur = pre.next;
                while(cur != null) {
                    if(cur.key == key) {
                        pre.next = cur.next;
                        cur = null;

                        table[idx] = dummy.next;
                        return;
                    }

                    pre = cur;
                    cur = cur.next;
                }
            }

            return;
        }
    }


    public static void main(String[] args) {
        MyHashMap hashMap = new MyHashMap();
//        hashMap.put(1, 1);
//        hashMap.put(2, 2);
//        System.out.println(hashMap.get(1));            // returns 1
//        System.out.println(hashMap.get(3));            // returns -1 (not found)
//        hashMap.put(2, 1);          // update the existing value
//        System.out.println(hashMap.get(2));            // returns 1
//        hashMap.remove(2);          // remove the mapping for 2
//        System.out.println(hashMap.get(2));            // returns -1 (not found)

        int a = 332;
        System.out.println(hashMap.hash(a));
    }


}
