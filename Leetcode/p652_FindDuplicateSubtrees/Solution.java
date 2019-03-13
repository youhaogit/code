package Leetcode.p652_FindDuplicateSubtrees;

import util.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    Map<String, Integer> count = new HashMap<>();
    List<TreeNode> result = new ArrayList<>();
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        if(root == null) {
            return result;
        }

        traverse(root);
        return result;
    }

    private String traverse(TreeNode root) {
        if(root == null) {
            return "#";
        }

        String serial = root.val + "," + traverse(root.left) + "," + traverse(root.right);
        count.put(serial, count.getOrDefault(serial, 0) + 1);

        if(count.get(serial) == 2) {
            result.add(root);
        }

        return  serial;
    }

    public static void main(String[] args) {
    }


}
