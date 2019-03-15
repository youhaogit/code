package Leetcode.p220_ContainsDuplicateIII;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Solution {
//
//    Given an array of integers, find out whether there are two distinct indices i and j
//    in the array such that
//    the absolute difference between nums[i] and nums[j] is at most t and
//    the absolute difference between i and j is at most k.
//
//    Example 1:
//
//    Input: nums = [1,2,3,1], k = 3, t = 0
//    Output: true

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0; i < nums.length; i++) {
            Integer ceil = set.ceiling(nums[i]);
            if(ceil != null && ceil <= nums[i] + t) {
                return true;
            }

            Integer floor = set.floor(nums[i]);
            if(floor != null && nums[i] - t >= floor) {
                return true;
            }

            set.add(nums[i]);
            if(set.size() > k) {
                set.remove(nums[i - k]);
            }
        }

        return false;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
    }
}