package Leetcode.p450_DeleteNodeinaBST;

import sun.reflect.generics.tree.Tree;
import util.TreeNode;

public class Solution {

    private TreeNode findSuccessor(TreeNode root, int key) {
        if(root == null) {
            return null;
        }

        TreeNode successor = null;
        if(root.right != null) {
            successor = root.right;
            while(successor.left != null) {
                successor = successor.left;
            }
        }

        return successor;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) {
            return root;
        }

        //found the node to be deleted
        if(root.val == key) {
            if(root.left == null) {
                return root.right;
            }
            if(root.right == null) {
                return root.left;
            }

            //has left and right child, swap val with its successor
            TreeNode successor = findSuccessor(root, key);
            root.val = successor.val;
            root.right = deleteNode(root.right, successor.val);

            return root;
        }

        //recursive call
        if(key < root.val) {
            root.left = deleteNode(root.left, key);
        }else {
            root.right = deleteNode(root.right, key);
        }

        return root;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
    }
}
