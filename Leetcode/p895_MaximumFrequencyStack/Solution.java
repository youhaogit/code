package Leetcode.p895_MaximumFrequencyStack;

import java.util.*;

public class Solution {

    class FreqStack {

        Map<Integer, Integer> freqMap;
        Map<Integer, Deque<Integer>> map;
        int maxFreq;

        public FreqStack() {
            maxFreq = 0;
            freqMap = new HashMap<>();
            map = new HashMap<>();
        }

        public void push(int x) {
            // update frequency
            int freq = freqMap.getOrDefault(x, 0) + 1;
            freqMap.put(x, freq);
            if(freq > maxFreq) {
                maxFreq = freq;
            }

            // push to the stack associated with specific frequency
            Deque<Integer> stack = map.getOrDefault(freq, new ArrayDeque<>());
            stack.push(x);
            map.put(freq, stack);
        }

        public int pop() {
            Deque<Integer> stack = map.get(maxFreq);
            int res = stack.pop();

            // update frequency for res
            int freq = freqMap.get(res);
            freq--;
            if(freq == 0) {
                freqMap.remove(res);
            }
            freqMap.put(res, freq);

            if(map.get(maxFreq).size() == 0) {
                maxFreq--;
            }
            return res;
        }
    }


    public static void main(String[] args) {
        Solution s = new Solution();
    }
}