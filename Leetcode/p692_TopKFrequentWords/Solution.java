package Leetcode.p692_TopKFrequentWords;

import java.util.*;


//Given a non-empty list of words, return the k most frequent elements.
//
//Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.
//
//Example 1:
//Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
//Output: ["i", "love"]
//Explanation: "i" and "love" are the two most frequent words.
//Note that "i" comes before "love" due to a lower alphabetical order.
//Example 2:
//Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
//Output: ["the", "is", "sunny", "day"]
//Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
//with the number of occurrence being 4, 3, 2 and 1 respectively.

public class Solution {

    public List<String> topKFrequent(String[] words, int k) {
        List<String> result = new ArrayList<>();
        if(words == null || words.length == 0 || k <= 0) {
            return result;
        }

        Map<String, Integer> map = new HashMap<>();
        for(String word: words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> heap = new PriorityQueue<>((a, b) -> {
            if(map.get(a) == map.get(b)) {
                return b.compareTo(a);
            }else {
                return map.get(a) - map.get(b);
            }
        });

        for(String word: map.keySet()) {
            heap.offer(word);
            if(heap.size() > k) {
                heap.poll();
            }
        }

        while(!heap.isEmpty()) {
            result.add(heap.poll());
        }

        return result;
    }

    public List<String> topKFrequentII(String[] words, int k) {
        List<String> result = new ArrayList<>();
        if(words == null || words.length == 0 || k <= 0) {
            return result;
        }

        Map<String, Integer> map = new HashMap<>();
        for(String word: words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }



        return result;
    }
}
