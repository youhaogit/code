package Leetcode.p957_PrisonCellsAfterNDays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int[] prisonAfterNDays(int[] cells, int N) {
        if(cells == null || cells.length == 0 || N == 0) {
            return cells;
        }

        Map<String, Integer> map = new HashMap<>();

        while(N > 0) {
            map.put(Arrays.toString(cells), N--);

            int[] cur = new int[8];
            for(int i = 1; i < 7; i++) {
                cur[i] = (cells[i - 1] == cells[i + 1]) ? 1 : 0;
            }
            cells = cur;

            if(map.containsKey(Arrays.toString(cells))) {
                N %= (map.get(Arrays.toString(cells)) - N);
            }
        }

        return cells;

    }

//    Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
//    Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
//    Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
//    Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
//    Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
//    Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
//    Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
//    Day 7: [0, 0, 1, 1, 0, 0, 0, 0]

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] cells = {0,1,0,1,1,0,0,1};
        System.out.println(cells.toString());
        System.out.println(Arrays.toString(cells));
//        System.out.println(s.prisonAfterNDays(cells, 7));
    }
}
