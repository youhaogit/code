package Indeed;

public class LongestSubArraySum {

//    给一个数组和一个target number，寻找一个subarray，要求这个subarry的和不大于target number，
//    返回满足这个要求的最长的subarray的长度。比如 nums =  [2, 5, 1, 6, 7, 9], target =9, 则返回 3,
//    因为满足条件的最长subarray是[2, 5, 1]。解法是用两个pointer做一遍scan

    public static int longestSubarray(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0, right = 0;
        int sum = 0;
        int ans = 0;
        while(right < nums.length) {
            sum += nums[right];

            if(sum >= target) {
                left = right;
                sum = nums[right];
            }else {
                ans = Math.max(ans, right - left + 1);
            }
            right++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {8,7,3,4,5};
        System.out.println(longestSubarray(nums, 2));
    }
}
