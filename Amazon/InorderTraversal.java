package Amazon;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class InorderTraversal {

//    public static List<Integer> inorderTraversal(TreeNode root) {
//        List<Integer> result = new ArrayList<>();
//        traverse(root, result);
//        return result;
//    }
//
//    private static void traverse(TreeNode root, List<Integer> result) {
//        if(root == null) {
//            return;
//        }
//
//        traverse(root.left, result);
//        result.add(root.val);
//        traverse(root.right, result);
//    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        while(!stack.isEmpty() || root != null) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode cur = stack.pop();
            result.add(cur.val);
            root = cur.right;
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(inorderTraversal(root));
    }
}
