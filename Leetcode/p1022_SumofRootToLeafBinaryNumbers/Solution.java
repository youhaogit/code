package Leetcode.p1022_SumofRootToLeafBinaryNumbers;

import Leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    int sum = 0;
    public int sumRootToLeaf(TreeNode root) {
        if(root == null) {
            return 0;
        }

        traverse(root, new StringBuilder());
        return sum;
    }

    private void traverse(TreeNode node, StringBuilder sb) {
        if(node == null) {
            return;
        }

        sb.append(node.val);
        if(node.left == null && node.right == null) {
            sum += Integer.parseInt(sb.toString(), 2);
        }else {
            traverse(node.left, sb);
            traverse(node.right, sb);
        }
        sb.deleteCharAt(sb.length() - 1);
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(1);

        System.out.println(s.sumRootToLeaf(root));
    }
}