package Leetcode.p637_AverageofLevelsinBinaryTree;

import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if(root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0.0;
            int counter = 0;
            for(int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                sum += cur.val;
                counter++;

                if(cur.left != null) {
                    queue.offer(cur.left);
                }
                if(cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            result.add(sum / counter);
        }

        return result;
    }

    public List<Double> averageOfLevelsDFS(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if(root == null) {
            return result;
        }

        List<Double> levelSum = new ArrayList<>();
        List<Integer> count = new ArrayList<>();

        dfs(root, 0, levelSum, count);

        for(int i = 0; i < levelSum.size(); i++) {
            result.add((double)levelSum.get(i) / count.get(i));
        }
        return result;
    }

    private void dfs(TreeNode root, int level, List<Double> levelSum, List<Integer> count) {
        if(root == null) {
            return;
        }

        if(level < levelSum.size()) {
            levelSum.set(level, levelSum.get(level) + root.val);
            count.set(level, count.get(level) + 1);
        }else {
            levelSum.add(1.0 * root.val);
            count.add(1);
        }

        dfs(root.left, level + 1, levelSum, count);
        dfs(root.right, level + 1, levelSum, count);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
    }
}