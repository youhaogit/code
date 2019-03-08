package Indeed;

import util.TreeNode;

public class SerializeAndDeserializeBST {

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) { // preorder
        StringBuilder sb = new StringBuilder();
        serializedfs(root, sb);
        return sb.toString();
    }

    private static void serializedfs(TreeNode root, StringBuilder sb){
        if(root == null) return; // no "null "
        sb.append(root.val).append(" ");
        serializedfs(root.left, sb);
        serializedfs(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data.length() == 0) return null;
        int[] pos = new int[1];
        pos[0] = 0;
        return buildTree(data.split(" "), pos, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static TreeNode buildTree(String[] nodes, int[] pos, int min, int max) {
        if (pos[0] == nodes.length) return null;

        int val = Integer.valueOf(nodes[pos[0]]);
        if (val < min || val > max) return null; // Go back if we are over the boundary
        TreeNode cur = new TreeNode(val);

        pos[0]++; // update current position
        cur.left = buildTree(nodes, pos, min, val);
        cur.right = buildTree(nodes, pos, val, max);
        return cur;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(8);

        String s = serialize(root);
        System.out.println(s);
        deserialize(s);
    }
}
