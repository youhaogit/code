package Amazon;

import sun.reflect.generics.tree.Tree;

import java.util.Deque;
import java.util.LinkedList;

public class PopulatingNextPointer {

    public static class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }

    // connect a general tree

    public static void connect(TreeLinkNode root) {
        if(root == null) {
            return;
        }

        Deque<TreeLinkNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            TreeLinkNode pre = null;
            for(int i = 0; i < size; i++) {
                TreeLinkNode cur = queue.poll();
                if(pre == null) {
                    pre = cur;
                }else {
                    pre.next = cur;
                    pre = cur;
                }


                if(cur.left != null) {
                    queue.offer(cur.left);
                }
                if(cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }

        return;

    }

    public static void main(String[] args) {
        TreeLinkNode node1 = new TreeLinkNode(1);
        TreeLinkNode node2 = new TreeLinkNode(2);
        TreeLinkNode node3 = new TreeLinkNode(3);
        TreeLinkNode node4 = new TreeLinkNode(4);
        TreeLinkNode node5 = new TreeLinkNode(5);
        TreeLinkNode node7 = new TreeLinkNode(7);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node7;

        connect(node1);
        System.out.println("hjaha");
    }
}
