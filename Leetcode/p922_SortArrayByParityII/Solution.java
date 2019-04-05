package Leetcode.p922_SortArrayByParityII;

public class Solution {

//    Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.
//
//    Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.
//
//    You may return any answer array that satisfies this condition.
//
//
//
//            Example 1:
//
//    Input: [4,2,5,7]
//    Output: [4,5,2,7]
//    Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.

    public int[] sortArrayByParityII(int[] A) {
        if(A == null || A.length <= 1) {
            return A;
        }

        int odd = 1;
        for(int i = 0; i < A.length; i += 2) {
            if(A[i] % 2 == 1) {

                //找到奇数位置上的偶数，对换
                while(A[odd] % 2 == 1) {
                    odd += 2;
                }

                int temp = A[i];
                A[i] = A[odd];
                A[odd] = temp;
            }
        }

        return A;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = {4,2,5,7};
        s.sortArrayByParityII(nums);
        for(int n: nums) {
            System.out.println(n);
        }
    }
}