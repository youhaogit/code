package Indeed;
import util.TreeNode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class SerializeAndDeserialize {

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {

        //serialize by dfs preorder traversal
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);
        return sb.toString();
    }

    private static void preorder(TreeNode root, StringBuilder sb) {

        if(root == null) {
            sb.append("null,");
            return;
        }

        sb.append(String.valueOf(root.val) + ",");
        preorder(root.left, sb);
        preorder(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        Deque<String> deque = new LinkedList<>();
        String[] arr = data.split(",");
        deque.addAll(Arrays.asList(arr));
        return build(deque);
    }

    private static TreeNode build(Deque<String> deque) {
        String cur = deque.poll();
        if(cur.equals("null")) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(cur));
        root.left = build(deque);
        root.right = build(deque);

        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        String s = serialize(root);
        System.out.println(s);
        deserialize(s);
    }
}
