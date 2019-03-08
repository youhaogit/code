package Indeed;

import util.ListNode;

import java.util.*;

public class MergeKSortedList {


//    Input:
//            [
//            1->4->5,
//            1->3->4,
//            2->6
//            ]
//    Output: 1->1->2->3->4->4->5->6

    public static ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> pq = new PriorityQueue<>((ListNode a, ListNode b) -> (a.val - b.val));
        ListNode dummy = new ListNode(-1);

        for(ListNode node: lists) {
            pq.offer(node);
        }

        ListNode tail = dummy;
        while(!pq.isEmpty()) {
            ListNode cur = pq.poll();
            tail.next = cur;
            if(cur.next != null) {
                pq.offer(cur.next);
            }
            tail = tail.next;
        }

        return dummy.next;
    }

    static class Node {
        int val;
        Iterator<Integer> iter;
        public Node(Iterator<Integer> iter) {
            this.iter = iter;
            this.val = iter.next();
        }
    }

    public static List<Integer> mergeKListsII(List<List<Integer>> lists) {
        if(lists == null || lists.size() == 0) {
            return null;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((Node a, Node b) ->(a.val - b.val));
        for(List<Integer> list: lists) {
            if(list != null && list.size() != 0) {
                pq.offer(new Node(list.iterator()));
            }
        }

        List<Integer> result = new ArrayList<>();
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            result.add(cur.val);
            if(cur.iter.hasNext()) {
                cur.val = cur.iter.next();
                pq.offer(cur);
            }
        }

        return result;

    }

    public static void main(String[] args) {
        Integer[] nums1 = {-1,0,2,4};
        Integer[] nums2 = {0,2,4,6};
        Integer[] nums3 = {1,3,4,5};
        List<Integer> list1 = new ArrayList<>();
        list1.addAll(Arrays.asList(nums1));

        List<Integer> list2 = new ArrayList<>();
        list2.addAll(Arrays.asList(nums2));

        List<Integer> list3 = new ArrayList<>();
        list3.addAll(Arrays.asList(nums3));

        List<List<Integer>> lists = new ArrayList<>();
        lists.add(list1);
        lists.add(list2);
        lists.add(list3);

        for(int n: mergeKListsII(lists)) {
            System.out.println(n);
        }

    }
}
