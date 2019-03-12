package Leetcode.p828_UniqueLetterString;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int uniqueLetterString(String S) {
        if(S == null || S.length() == 0) {
            return 0;
        }

        int sum = 0;
        for(int i = 0; i < S.length(); i++) {
            for(int j = i + 1; j <= S.length(); j++) {
                Map<Character, Integer> map = new HashMap<>();
                String substring = S.substring(i, j);
                for(char c: substring.toCharArray()) {
                    map.put(c, map.getOrDefault(c, 0) + 1);
                }

                for(char c: map.keySet()) {
                    if(map.get(c) == 1) {
                        sum += 1;
                    }
                }

                sum %= Math.pow(10, 9) + 7;
            }
        }

        return sum;
    }

    public int uniqueLetterStringWoW(String S) {
        if(S == null || S.length() == 0) {
            return 0;
        }

        int[][] index = new int[26][2];
        for(int[] row: index) {
            Arrays.fill(row, -1);
        }

        int res = 0;
        int N = S.length();
        int mod = (int)Math.pow(10, 9) + 7;

        for(int i = 0; i < N; i++) {
            int idx = S.charAt(i) - 'A';
            res = (res + (i - index[idx][1]) * (index[idx][1] - index[idx][0]) % mod) % mod;
            index[idx] = new int[]{index[idx][1], i};
        }

        for(int i = 0; i < index.length; i++) {
            res = res + ((N - index[i][1]) * (index[i][1] - index[i][0])) % mod;
        }

        return res;
    }

    public static void main(String[] args) {
        String str = "ABC";
        Solution s = new Solution();
        System.out.println(s.uniqueLetterString(str));
    }
}
