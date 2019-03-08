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

    public static void main(String[] args) {
        int[] nums1 = {3,2,1,0,4};
        System.out.println(canJump(nums1));
    }
}
