package Amazon;

import java.util.Deque;
import java.util.LinkedList;

public class PartitionList {

//    Input: head = 1->4->3->2->5->2, x = 3
//    Output: 1->2->2->4->3->5
    public static ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode leftDummy = new ListNode(-1);
        ListNode rightDummy = new ListNode(-1);
        ListNode left = leftDummy;
        ListNode right = rightDummy;

        while(head != null) {
            if(head.val < x) {
                left.next = head;
                left = left.next;
            }else {
                right.next = head;
                right = right.next;
            }
            head = head.next;
        }

        left.next = rightDummy.next;
        right.next = null;

        return leftDummy.next;
    }

    public static ListNode partitionII(ListNode head, int x) {
        if(head == null) {
            return head;
        }

        ListNode small = new ListNode(-1);
        ListNode swalker = small;
        ListNode big = new ListNode(-1);
        ListNode bwalker = big;

        while(head != null) {
            if(head.val < x) {
                swalker.next = head;
                swalker = swalker.next;
            }else {
                bwalker.next = head;
                bwalker = bwalker.next;
            }
            head = head.next;
        }

        bwalker.next = null;
        swalker.next = big.next;

        return small.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode n1 = new ListNode(4);
        ListNode n2= new ListNode(3);
        ListNode n3 = new ListNode(2);
        ListNode n4 = new ListNode(5);
        ListNode n5 = new ListNode(2);
        head.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        ListNode cur = partitionII(head, 3);
        while(cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }

}
