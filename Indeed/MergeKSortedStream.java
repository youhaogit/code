package Indeed;

import java.util.*;

public class MergeKSortedStream {

    static class MyNodeComparator implements Comparator<Node>{
        @Override
        public int compare(Node a, Node b) {
            return a.val - b.val;
        }
    }


    static class Node {
        int val;
        Iterator<Integer> iterator;

        public Node(Iterator<Integer> iterator) {
            this.iterator = iterator;
            val = iterator.next();
        }
    }

    public static List<Integer> findMoreThanKTimes(List<List<Integer>> lists, int k) {
        if (lists == null || lists.size() == 0) {
            return null;
        }

        List<Integer> result = new ArrayList<>();
        PriorityQueue<Node> minPQ = new PriorityQueue<>(new MyNodeComparator());

        // step 1: put the first node of each list into the queue
        for (List<Integer> list : lists) {
            if (list != null  && !list.isEmpty()) {
                minPQ.offer(new Node(list.iterator()));
            }
        }

        while (!minPQ.isEmpty()) {
            Node curr = minPQ.poll();
            int currVal = curr.val;
            int count = 1;

            // put the next node into pq, skip the repeated element
            while (curr.iterator.hasNext()) {
                int nextVal = curr.iterator.next();
                if (currVal == nextVal) {
                    continue;
                } else {
                    curr.val = nextVal;
                    minPQ.offer(curr);
                    break;
                }
            }

            // get all repeated elements from the pq
            while (!minPQ.isEmpty() && currVal == minPQ.peek().val) {
                count++;
                Node node = minPQ.poll();
                int nodeVal = node.val;

                // put the next node into pq, skip the repeated elements
                while (node.iterator.hasNext()) {
                    int nextNodeVal = node.iterator.next();
                    if (nodeVal == nextNodeVal) {
                        continue;
                    } else {
                        node.val = nextNodeVal;
                        minPQ.offer(node);
                        break;
                    }
                }
            }

            if (count >= k) {
                result.add(currVal);
            }
        }

       return result;
    }


    public static List<Integer> findMoreThanKTimesII(List<List<Integer>> lists, int k) {
        List<Integer> result = new ArrayList<>();
        if(lists == null || lists.size() == 0 || k <= 0) {
            return result;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((Node a, Node b) -> (a.val - b.val));
        for(List<Integer> list: lists) {
            if (list != null && !list.isEmpty()) {
                pq.offer(new Node(list.iterator()));
            }
        }

        while(!pq.isEmpty()) {
            int cnt = 1;
            Node head = pq.poll();
            int headVal = head.val;

            while(head.iterator.hasNext()) {
                int headNextVal = head.iterator.next();
                if(headNextVal == headVal) {
                    continue;
                }else {
                    head.val = headNextVal;
                    pq.offer(head);
                    break;
                }
            }

            while(!pq.isEmpty() && headVal == pq.peek().val) {
                cnt++;
                Node node = pq.poll();
                int nodeVal = node.val;

                while(node.iterator.hasNext()) {
                    int nodeNextVal = node.iterator.next();
                    if(nodeNextVal == nodeVal) {
                        continue;
                    }else {
                        node.val = nodeNextVal;
                        pq.offer(node);
                        break;
                    }
                }
            }

            if(cnt >= k) {
                result.add(headVal);
            }
        }

        return result;
    }

    public static void main(String[] args) {

        Integer[] a = new Integer[]{1, 4};
        Integer[] b = new Integer[]{2, 3, 4};
        Integer[] c = new Integer[]{1, 2, 3, 3, 4};

        List<List<Integer>> lists = new ArrayList<>();
        lists.add(Arrays.asList(a));
        lists.add(Arrays.asList(b));
        lists.add(Arrays.asList(c));

        List<Integer> result = findMoreThanKTimesII(lists, 2);

        for (Integer e : result) {
            System.out.print(e + " ");
        }

        System.out.println(" ");
    }


}