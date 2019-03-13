package Leetcode.p798_SmallestRotationwithHighestScore;

import java.util.Arrays;

public class Solution {

    public int bestRotation(int[] A) {

        int[] result = new int[A.length];

        int n = A.length;
        for(int i = 0; i < n; i++){

            // get rotated array
            int[] arr = Arrays.copyOf(A, A.length);
            reverse(arr, 0, arr.length - 1);
            reverse(arr, 0, arr.length - i - 1);
            reverse(arr, arr.length - i, arr.length - 1);

            int score = 0;
            for(int j = 0; j < arr.length; j++) {
                if(arr[j] <= j) {
                    score++;
                }
            }

            result[i] = score;
        }

        int min = 0;
        for(int i = 1; i < result.length; i++) {
            if(result[i] > result[min]) {
                min = i;
            }
        }

        return min;
    }

    private void reverse(int[] nums, int left, int right) {
        while(left < right) {
            int temp = nums[right];
            nums[right] = nums[left];
            nums[left] = temp;

            right--;
            left++;
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] A = new int[]{2,3,1,4,0};
        System.out.println(s.bestRotation(A));
    }
}
