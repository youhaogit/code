package Amazon;

public class MaxSubarraySum {

    //Divide and conquer approach
    public static int maxSubarraySum(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        return maxSubarraySum(nums, 0, nums.length - 1);
    }

    private static int maxSubarraySum(int[] nums, int left, int right) {
        if(left == right) {
            return nums[left];
        }

        int sum = 0;
        for(int i = left; i <= right; i++) {
            sum += nums[i];
        }

        int mid = (left + right) / 2;
        int leftSum = maxSubarraySum(nums, left, mid);
        int rightSum = maxSubarraySum(nums, mid + 1, right);
        return Math.max(Math.max(leftSum, rightSum), sum);
    }

    public static void main(String[] args) {
        int[] nums = {2,3,-1, -5,6};
        System.out.println(maxSubarraySum(nums));
    }
}
