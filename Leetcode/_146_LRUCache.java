package Leetcode;

import java.util.*;

public class _146_LRUCache {

    class LRUCache extends LinkedHashMap<Integer, Integer> {

        private int capacity;

        public LRUCache(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }

    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4));
        Integer[] nums = list.toArray(new Integer[0]);
        for(Integer num: nums) {
            System.out.println(num);
        }

        nums[0] = 5;
        for(Integer num: nums) {
            System.out.println(num);
        }
    }
}
