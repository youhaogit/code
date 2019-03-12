package Leetcode.p1008_ConstructBinarySearchTreefromPreorderTraversal;

import Leetcode.util.TreeNode;

public class Solution {

    int i = 0;
    public TreeNode bstFromPreorder(int[] preorder) {
        return build(preorder, Integer.MAX_VALUE);
    }

    private TreeNode build(int[] preorder, int bound) {
        if(i == preorder.length || preorder[i] > bound) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[i++]);
        root.left = build(preorder, root.val);
        root.right = build(preorder, bound);

        return root;
    }

}
