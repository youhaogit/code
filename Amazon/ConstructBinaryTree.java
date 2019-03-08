package Amazon;

public class ConstructBinaryTree {

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }

        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private static TreeNode build(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft, int inRight) {
        if(preLeft > preRight || inLeft > inRight) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preLeft]);
        int idx = -1;
        for(int i = inLeft; i <= inRight; i++) {
            if(inorder[i] == root.val) {
                idx = i;
                break;
            }
        }

        int leftLength = idx - inLeft;
        System.out.println(idx - 1);
        System.out.println(inLeft + leftLength - 1);
        root.left = build(preorder, preLeft + 1, preLeft + leftLength, inorder, inLeft, idx - 1);
        root.right = build(preorder,preLeft + leftLength + 1, preRight, inorder, idx + 1, inRight);

        return root;
    }

    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};

        System.out.println(buildTree(preorder, inorder));
    }
}
