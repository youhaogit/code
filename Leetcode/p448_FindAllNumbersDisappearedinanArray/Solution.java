package Leetcode.p448_FindAllNumbersDisappearedinanArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ret = new ArrayList<Integer>();

        for(int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]) - 1;
            if(nums[val] > 0) {
                nums[val] = -nums[val];
            }
        }

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > 0) {
                ret.add(i+1);
            }
        }
        return ret;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = {4,3,2,7,8,2,3,1};
        System.out.println(s.findDisappearedNumbers(nums));
    }
}