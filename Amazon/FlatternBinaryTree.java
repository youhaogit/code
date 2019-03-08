package Amazon;

public class FlatternBinaryTree {

    public static void flatten(TreeNode root) {
        if(root == null)
            return;

        TreeNode cur = root;
        while(cur != null) {
            if(cur.left != null) {
                TreeNode pre = cur.left;
                while(pre.right != null) {
                    pre = pre.right;
                }

                pre.right = cur.right;
                cur.left = null;

                cur = cur.right;
            }
        }

    }
}
