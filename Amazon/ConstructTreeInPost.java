package Amazon;

public class ConstructTreeInPost {
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder == null || postorder == null || inorder.length != postorder.length) {
            return null;
        }

        return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private static TreeNode build(int[] inorder, int inLeft, int inRight, int[] postorder, int postLeft, int postRight) {
        if(inLeft > inRight || postLeft > postRight) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postRight]);
        int idx = -1;
        for(int i = inLeft; i <= inRight; i++) {
            if(inorder[i] == root.val) {
                idx = i;
                break;
            }
        }

        int leftLength = idx - inLeft;
        root.left = build(inorder, inLeft, idx - 1, postorder, postLeft, postLeft + leftLength - 1);
        root.right = build(inorder, idx + 1, inRight, postorder, postLeft + leftLength, postRight - 1);

        return root;
    }

    public static void main(String[] args) {

    }
}
