package Leetcode.p218_TheSkylineProblem;

import java.util.*;


//    For instance, the dimensions of all buildings in Figure A are recorded as:
//        [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .


public class Solution {

    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
        if(buildings == null || buildings.length == 0) {
            return result;
        }

        List<int[]> height = new ArrayList<>();
        for(int[] building: buildings) {
            height.add(new int[] {building[0], -building[2]});
            height.add(new int[] {building[1], building[2]});
        }

        Collections.sort(height, (a, b) -> {
            if(a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        pq.offer(0);

        int prev = 0;
        for(int[] h: height) {
            if(h[1] < 0) {
                pq.offer(-h[1]);
            }else {
                pq.remove(h[1]);
            }

            int cur = pq.peek();

            if(prev != cur) {
                result.add(new int[] {h[0], cur});
                prev = cur;
            }
        }

        return result;
    }

}
