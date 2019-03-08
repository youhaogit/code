package Amazon;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode next;
    TreeNode(int x){
        val = x;
    }

    public String toString(){
        String str = "";
        return str + this.val;
    }
}
