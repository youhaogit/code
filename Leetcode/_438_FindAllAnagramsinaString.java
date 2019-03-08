package Leetcode;

import java.util.*;

public class _438_FindAllAnagramsinaString {
//    Input:
//    s: "cbaebabacd" p: "abc"
//
//    Output:
//            [0, 6]
//
//    Explanation:
//    The substring with start index = 0 is "cba", which is an anagram of "abc".
//    The substring with start index = 6 is "bac", which is an anagram of "abc".

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if(s == null || p == null || p.length() > s.length()) {
            return result;
        }

        Map<Character, Integer> map = new HashMap<>();
        for(char c: p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int start = 0, end = 0;
        int counter = map.size();

        while(end < s.length()) {
            if(map.containsKey(s.charAt(end))) {
                map.put(s.charAt(end), map.get(s.charAt(end)) - 1);
                if(map.get(s.charAt(end)) == 0) {
                    counter--;
                }
            }
            end++;

            while (counter == 0) {
                if(map.containsKey(s.charAt(start))) {
                    map.put(s.charAt(start), map.get(s.charAt(start)) + 1);
                    if(map.get(s.charAt(start)) > 0) {
                        counter++;
                    }
                }

                if(end - start == p.length()) {
                    result.add(start);
                }
                start++;
            }
        }


        return result;
    }


    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        System.out.println(findAnagrams(s, p));
    }
}
