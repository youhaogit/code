package Amazon;

import java.util.ArrayList;
import java.util.List;

public class MergeIntervals {
    static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }

        public String toString() {
            return "[" + this.start + "," + this.end + "]";
        }
    }

    static class IntervalTree {
        IntervalTree left, right;
        int low, high, max;
        public IntervalTree(int low, int high) {
            this.low = low;
            this.high = high;
            this.max = high;
            this.left = null;
            this.right = null;
        }
    }

    public static List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();
        if(intervals == null || intervals.size() == 0) {
            return result;
        }

        IntervalTree root = new IntervalTree(intervals.get(0).start, intervals.get(0).end);
        for(int i = 1; i < intervals.size(); i++) {
            insertNode(root, intervals.get(i));
        }

        traverse(root, result);
        return result;

    }

    private static IntervalTree insertNode(IntervalTree root, Interval interval) {

        if(root == null) {
            return new IntervalTree(interval.start, interval.end);
        }


        if(interval.end < root.low) {
            root.left = insertNode(root.left, interval);
        }else if(interval.start > root.high) {
            root.right = insertNode(root.right, interval);
        }else {
            root.low = Math.min(root.low, interval.start);
            root.high = Math.max(root.high, interval.end);
            if(root.max < interval.end) {
                root.right = null;
            }
        }

        root.max = Math.max(root.max, interval.end);
        return root;
    }

    private static void traverse(IntervalTree root, List<Interval> result) {
        if(root == null) {
            return;
        }
        result.add(new Interval(root.low, root.high));
        traverse(root.left, result);
        traverse(root.right, result);
    }

    public static void main(String[] args) {
        Interval i1 = new Interval(2,3);
        Interval i2 = new Interval(5,5);
        Interval i3 = new Interval(2,2);
        Interval i4 = new Interval(3,4);
        Interval i5 = new Interval(3,4);
        List<Interval> list = new ArrayList<>();
        list.add(i1);
        list.add(i2);
        list.add(i3);
        list.add(i4);
        list.add(i5);
        System.out.println(merge(list));

    }
}
