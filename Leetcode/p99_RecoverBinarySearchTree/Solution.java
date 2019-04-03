package Leetcode.p99_RecoverBinarySearchTree;

import util.TreeNode;

public class Solution {

//    Example 1:
//
//    Input: [1,3,null,null,2]
//
//            1
//            /
//           3
//            \
//            2
//
//    Output: [3,1,null,null,2]
//
//            3
//            /
//           1
//            \
//            2

    // general idea it to inorder traversal to find the node with wrong pos

    TreeNode firsetEle = null;
    TreeNode secondEle = null;
    TreeNode prevEle = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree(TreeNode root) {
        if(root == null) {
            return;
        }

        inorderTraverse(root);

        if(firsetEle != null && secondEle != null) {
            int temp = secondEle.val;
            secondEle.val = firsetEle.val;
            firsetEle.val = temp;
        }

        return;
    }

    private void inorderTraverse(TreeNode root) {
        if(root == null) {
            return;
        }

        inorderTraverse(root.left);

        if(firsetEle == null && prevEle.val >= root.val) {
            firsetEle = prevEle;
        }
        if(firsetEle != null && prevEle.val >= root.val) {
            secondEle = root;
        }
        prevEle = root;

        inorderTraverse(root.right);
    }


    public static void main(String[] args) {
        Solution s = new Solution();
    }
}