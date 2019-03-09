package Leetcode.p957_PrisonCellsAfterNDays;

public class Solution {

    public int[] prisonAfterNDays(int[] cells, int N) {
        if(cells == null || cells.length == 0 || N == 0) {
            return cells;
        }

        //temp array to record the state of previous day
        int[] temp = new int[cells.length];
        System.arraycopy(cells, 0, temp, 0, cells.length);

        for(int i = 1; i <= N; i++) {
            int[] cur = new int[cells.length];
            for(int j = 0; j < cells.length; j++) {
                if(j == 0 || j == cells.length - 1) {
                    cur[j] = 0;
                }else {
                    if((temp[j - 1] == 1 && temp[j + 1] == 1) || (temp[j - 1] == 0 && temp[j + 1] == 0)) {
                        cur[j] = 1;
                    }else {
                        cur[j] = 0;
                    }
                }
            }
            temp = cur;
        }

        return temp;

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
        System.out.println(s.prisonAfterNDays(cells, 7));
    }
}
