package Leetcode.p149_MaxPointsonaLine;

import java.util.HashMap;
import java.util.Map;

public class Solution {

//Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
//
//        Example 1:
//
//    Input: [[1,1],[2,2],[3,3]]
//    Output: 3
//    Explanation:
//    ^
//    |
//    |        o
//    |     o
//    |  o
//    +------------->
//    0  1  2  3  4

    static class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    public int maxPoints(Point[] points) {
        if(points == null || points.length <= 0)
            return 0;
        if(points.length <= 2)
            return points.length;

        int result = 0;
        for(int i = 0; i < points.length; i++){
            HashMap<Double, Integer> hm = new HashMap<>();
            int samex = 1;
            int samep = 0;

            for(int j = 0; j < points.length; j++){
                if(j != i){
                    if((points[j].x == points[i].x) && (points[j].y == points[i].y)){
                        samep++;
                    }
                    if(points[j].x == points[i].x){
                        samex++;
                        continue;
                    }

                    double k = (double)(points[j].y - points[i].y) / (double)(points[j].x - points[i].x);
                    if(hm.containsKey(k)){
                        hm.put(k,hm.get(k) + 1);
                    }else{
                        hm.put(k, 2);
                    }

                    result = Math.max(result, hm.get(k) + samep);
                }
            }

            result = Math.max(result, samex);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        Point a = new Point(1,1);
        Point b = new Point(2,2);
        Point c = new Point(3,3);

        Point[] points = new Point[]{a, b, c};
        System.out.println(s.maxPoints(points));

    }
}
