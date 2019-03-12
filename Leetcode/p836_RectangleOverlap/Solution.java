package Leetcode.p836_RectangleOverlap;

public class Solution {

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        if(rec1 == null || rec2 == null) {
            return false;
        }

        int left = Math.max(rec1[0], rec2[0]);
        int right = Math.max(Math.min(rec1[2], rec2[2]), left);

        int bottom = Math.max(rec1[1], rec2[1]);
        int top = Math.max(bottom, Math.min(rec1[3], rec2[3]));

        return (top - bottom) * (right - left) > 0;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] rec1 = {4,0,6,6};
        int[] rec2 = {-5,-3,4,2};
        System.out.println(s.isRectangleOverlap(rec1, rec2));
    }

}
