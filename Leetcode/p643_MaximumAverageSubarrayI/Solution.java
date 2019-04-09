package Leetcode.p643_MaximumAverageSubarrayI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        int[] prefix = new int[n];
        for(int i = 0; i < nums.length; i++) {
            prefix[i] = nums[i] + ((i == 0) ? 0 : prefix[i - 1]);
        }

        double res = Double.MIN_VALUE;
        for(int i = k - 1; i < prefix.length; i++) {
            double ans = 1.0 * (prefix[i] - (i == k - 1 ? 0 : prefix[i - k])) / k;
            res = Math.max(res, ans);
        }

        return res;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = {-1};
        System.out.println(s.findMaxAverage(nums, 1));
    }
}