package Amazon;

//求子树中平均值最大的子树, 不包括叶子节点
public class MaxAvgSubtree {

    static class ResultType {
        int sum;
        int count;

        public ResultType(int sum, int count) {
            this.sum = sum;
            this.count = count;
        }
    }

    private static MultiTreeNode result;
    private static double resAvg = Double.MIN_VALUE;
    public static MultiTreeNode MaxAvgSubtree(MultiTreeNode root) {
        if(root == null) {
            return null;
        }

        dfs(root);
        return result;
    }

    private static ResultType dfs(MultiTreeNode root) {
        if(root.children == null || root.children.size() == 0) {
            return new ResultType(root.val, 1);
        }

        //考虑子节点
//        if(root.children == null || root.children.size() == 0) {
//            if(root.val > resAvg) {
//                resAvg = root.val;
//                result = root;
//            }
//            return new ResultType(root.val, 1);
//        }

        int curSum = root.val;
        int curCount = 1;
        for(MultiTreeNode child: root.children) {
            ResultType childResult = dfs(child);
            curSum += childResult.sum;
            curCount += childResult.count;
        }

        double curAvg = (double) curSum / curCount;
        if(resAvg < curAvg) {
            resAvg = curAvg;
            result = root;
        }

        return new ResultType(curSum, curCount);
    }

    public static void main(String[] args) {
        MultiTreeNode root = new MultiTreeNode(1);
        MultiTreeNode l21 = new MultiTreeNode(2);
        MultiTreeNode l22 = new MultiTreeNode(3);
        MultiTreeNode l23 = new MultiTreeNode(7);
        MultiTreeNode l31 = new MultiTreeNode(5);
        MultiTreeNode l32 = new MultiTreeNode(5);
        MultiTreeNode l33 = new MultiTreeNode(5);
        MultiTreeNode l34 = new MultiTreeNode(5);
        MultiTreeNode l35 = new MultiTreeNode(5);
        MultiTreeNode l36 = new MultiTreeNode(5);

        l21.children.add(l31);
        l21.children.add(l32);

        l22.children.add(l33);
        l22.children.add(l34);

        l23.children.add(l35);
        l23.children.add(l36);

        root.children.add(l21);
        root.children.add(l22);
        root.children.add(l23);

        MultiTreeNode res = MaxAvgSubtree(root);
        System.out.println(res.val + " " + resAvg);
    }
}
