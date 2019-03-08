package Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _763_PartitionLabels {

//    Input: S = "ababcbacadefegdehijhklij"
//    Output: [9,7,8]
//    Explanation:
//    The partition is "ababcbaca", "defegde", "hijhklij".
//    This is a partition so that each letter appears in at most one part.
//    A partition like "ababcbacadefegde", "hijhklij" is incorrect,
//    because it splits S into less parts.

    public static List<Integer> partitionLabels(String S) {
        List<Integer> result = new ArrayList<>();
        if(S == null) {
            return result;
        }else if(S.length() == 0) {
            result.add(0);
            return result;
        }

        //map to store the last occurrence of each character
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < S.length(); i++) {
            map.put(S.charAt(i), i);
        }

        int start = 0, end = map.get(S.charAt(start));
        while(end < S.length()) {
            for(int i = start; i < end; i++) {
                if(map.get(S.charAt(i)) > end) {
                    end = map.get(S.charAt(i));
                }
            }

            result.add(end - start + 1);
            start = end + 1;

            if(start < S.length())
                end = map.get(S.charAt(start));
            else
                break;
        }

        return result;
    }

    public static void main(String[] args) {
        String S = "ababcbacadefegdehijhklij";
        System.out.println(partitionLabels(S));
    }
}
