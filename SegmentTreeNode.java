public class SegmentTreeNode {

    int max, start, end;
    SegmentTreeNode left, right;

    SegmentTreeNode(int start, int end, int sum){
        this.max = sum;
        this.start = start;
        this.end = end;
        left = null;
        right = null;
    }

    public static SegmentTreeNode build(int start, int end, int[] nums) {
        if(start > end) {
            return null;
        }

        SegmentTreeNode root = new SegmentTreeNode(start, end, nums[start]);
        if(start == end) {
            return root;
        }

        int mid = (start + end) / 2;
        root.left = build(start, mid, nums);
        root.right = build(mid + 1, end, nums);

        root.max = Math.max(root.left.max, root.right.max);

        return root;
    }

    public static int query(SegmentTreeNode root, int start, int end) {
        if(start == root.start && end == root.end) {
            return root.max;
        }

        int left = Integer.MIN_VALUE;
        int right = Integer.MIN_VALUE;
        int mid = (root.start + root.end) / 2;

        if(start <= mid) {
            left =  query(root.left, start, Math.min(mid, end));
        }

        if(mid < end) {
            right = query(root.right, Math.max(mid + 1, start), end);
        }

        return Math.max(left, right);
    }
}
