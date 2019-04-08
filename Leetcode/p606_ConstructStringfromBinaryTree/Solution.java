package Leetcode.p606_ConstructStringfromBinaryTree;

import Leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    StringBuilder sb;
    public String tree2str(TreeNode t) {
        sb = new StringBuilder();
        dfs(t);
        return sb.toString();
    }

    private void dfs(TreeNode cur) {


        if(cur == null) {
            return;
        }else{
            sb.append(cur.val);

            sb.append("(");
            dfs(cur.left);
            dfs(cur.right);
            sb.append(")");
        }


    }


    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);

        System.out.println(s.tree2str(root));
    }
}