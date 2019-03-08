package Amazon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKList {

    static class Node {
        int val;
        Iterator<Integer> iter;

        Node(Iterator<Integer> it) {
            this.iter = it;
            this.val = it.next();
        }
    }


    public static List<Integer> merge(List<List<Integer>> input) {

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> (a.val - b.val));
        for(List<Integer> list: input) {
            if(list != null || !list.isEmpty()) {
                pq.offer(new Node(list.iterator()));
            }
        }

        List<Integer> result = new ArrayList<>();
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            Iterator<Integer> iter = cur.iter;
            result.add(cur.val);

            if(iter.hasNext()) {
                pq.offer(new Node(iter));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>();
        l1.add(1);
        l1.add(2);
        l1.add(3);

        List<Integer> l2 = new ArrayList<>();
        l2.add(4);
        l2.add(5);
        l2.add(9);

        list.add(l1);
        list.add(l2);

        System.out.println(merge(list));

    }
}
