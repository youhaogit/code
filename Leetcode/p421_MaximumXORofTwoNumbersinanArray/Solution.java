package Leetcode.p421_MaximumXORofTwoNumbersinanArray;

public class Solution {

//    Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.
//
//    Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
//
//    Could you do this in O(n) runtime?
//
//    Example:
//
//    Input: [3, 10, 5, 25, 2, 8]
//
//    Output: 28
//
//    Explanation: The maximum result is 5 ^ 25 = 28.

    public int findMaximumXOR(int[] nums) {
        int res = nums[0];
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                int s = nums[i] ^ nums[j];
                res = Math.max(res, s);
            }
        }

        return res;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
    }
}