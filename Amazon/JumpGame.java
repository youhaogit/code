package Amazon;

public class JumpGame {

    enum Index {
        GOOD, BAD, X
    }

    static Index[] memo;
    public static boolean canJump(int[] nums) {
        if(nums == null || nums.length == 0) {
            return false;
        }

        memo = new Index[nums.length];
        for(int i = 0; i < nums.length; i++) {
            memo[i] = Index.X;
        }
        memo[nums.length - 1] = Index.GOOD;

        for(int i = nums.length - 2; i >= 0; i--) {
            int step = Math.min(nums[i], nums.length - 1);
            for(int j = i + 1; j < nums.length; j++) {
                if(memo[j] == Index.GOOD) {
                    memo[i] = Index.GOOD;
                    break;
                }
            }
        }

        return memo[0] == Index.GOOD;

    }

    public static boolean canJumpI(int[] nums) {
        // dp solution
        // dp[i] = 0 means unkown, dp[i] = 1 means good, dp[i] = -1 means bad
        if(nums == null || nums.length == 0) {
            return false;
        }

        int[] dp = new int[nums.length];
        dp[nums.length - 1] = 1;

        for(int i = nums.length - 2; i >= 0; i--) {
            int maxStep = Math.min(nums[i], nums.length - 1);
            for(int j = i + 1; j <= i + maxStep; j++) {
                if(dp[j] == 1) {
                    dp[i] = 1;
                    break;
                }
            }
        }

        return dp[0] == 1;
    }

    public static void main(String[] args) {
        int[] nums1 = {2,3,1,1,4};
        System.out.println(canJump(nums1));
        System.out.println(canJumpI(nums1));
    }
}
