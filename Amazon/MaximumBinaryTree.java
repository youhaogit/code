package Amazon;

public class MaximumBinaryTree {

    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        if(nums == null || nums.length == 0) {
            return null;
        }

        return helper(nums, 0, nums.length - 1);
    }

    private static TreeNode helper(int[] nums, int left, int right) {
        if(left > right || left < 0 || right > nums.length - 1) {
            return null;
        }

        int maxIdx = findMax(nums, left, right);
        TreeNode root = new TreeNode(nums[maxIdx]);

        root.left = helper(nums, left, maxIdx - 1);
        root.right = helper(nums, maxIdx + 1, right);

        return root;
    }

    private static int findMax(int[] nums, int left, int right) {
        int res = -1;
        int value = Integer.MIN_VALUE;
        for(int i = left; i <= right; i++) {
            if(nums[i] > value) {
                res = i;
                value = nums[i];
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,1,6,0,5};
        constructMaximumBinaryTree(nums);
    }
}
