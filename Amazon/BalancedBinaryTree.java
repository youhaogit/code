package Amazon;

import sun.reflect.generics.tree.Tree;

public class BalancedBinaryTree {

    static class ResultType {
        int height;
        boolean balanced;
        public ResultType(int height, boolean balanced) {
            this.height = height;
            this.balanced = balanced;
        }
    }

    public boolean isBalanced(TreeNode root) {
        if(root == null) {
            return true;
        }

        return helper(root).balanced;
    }

    private static ResultType helper(TreeNode root) {
        if(root == null) {
            return new ResultType(0, true);
        }

        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        return new ResultType(Math.max(left.height, right.height) + 1, left.balanced && right.balanced && Math.abs(left.height - right.height) <= 1);
    }

    public static void main(String[] args) {

    }
}
