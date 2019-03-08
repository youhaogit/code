package Amazon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SymmetricTree {

    public static boolean isSymmetric(TreeNode root) {
        if(root == null) {
            return true;
        }

        //bfs to check level by level
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                level.add(cur.left == null ? -1 : cur.left.val);
                level.add(cur.right == null ? - 1 : cur.right.val);

                if(cur.left != null) {
                    queue.offer(cur.left);
                }
                if(cur.right != null) {
                    queue.offer(cur.right);
                }
            }

            if(!isValid(level)) {
                return false;
            }
        }

        return true;
    }

    private static boolean isValid(List<Integer> level) {
        int left = 0, right = level.size() -1;
        while(left < right) {
            if(level.get(left++) != level.get(right--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        System.out.println(isSymmetric(root));
    }
}
