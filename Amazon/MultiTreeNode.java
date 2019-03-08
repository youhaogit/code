package Amazon;

import java.util.ArrayList;

public class MultiTreeNode {
    int val;
    ArrayList<MultiTreeNode> children;

    public MultiTreeNode(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }
}
