package Leetcode.p333_LargestBSTSubtree;

import util.TreeNode;

public class Solution {

    class ResultType {
        int lower;
        int upper;
        int count;

        ResultType(int lower, int upper, int count) {
            this.lower = lower;
            this.upper = upper;
            this.count = count;
        }
    }

    int max = 0;
    public int largestBSTSubtree(TreeNode root) {
        if(root == null) {
            return max;
        }

        traverse(root);

        return max;
    }

    private ResultType traverse(TreeNode root) {
        if(root == null) {
            return new ResultType(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }

        ResultType left = traverse(root.left);
        ResultType right = traverse(root.right);

        if(left.count == -1 || right.count == -1 || root.val < left.upper || root.val > right.lower) {
            return new ResultType(0, 0, -1);
        }

        int count = left.count + right.count + 1;
        max = Math.max(max, count);

        return new ResultType(Math.min(left.lower, root.val), Math.max(right.upper, root.val), count);
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
//        root.right = new TreeNode(2);
//        root.left.left = new TreeNode(1);
//        root.left.right = new TreeNode(8);
//        root.right.right = new TreeNode(7);

        System.out.println(s.largestBSTSubtree(root));
    }
}