package Amazon;

import java.util.HashSet;
import java.util.Set;

public class TwoSumIV {

    public static boolean findTarget(TreeNode root, int k) {
        if(root == null) {
            return false;
        }

        Set<Integer> set = new HashSet<>();
        // set.add(k - root.val);

        while(root != null) {
            if(set.contains(root.val)) {
                return true;
            }

            set.add(k - root.val);
            if(k - root.val < root.val) {
                root = root.left;
            }else {
                root = root.right;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        System.out.println(findTarget(root, 4));
    }
}
