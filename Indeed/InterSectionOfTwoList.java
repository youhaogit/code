package Indeed;

import util.ListNode;

import java.util.List;

public class InterSectionOfTwoList {


    public static ListNode intersection(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) {
            return null;
        }

        ListNode tailA = headA;
        while(tailA.next != null) {
            tailA = tailA.next;
        }
        tailA.next = headB;
        ListNode result = helper(headA);
        tailA.next = null;

        return result;
    }

    private static ListNode helper(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while(slow != fast) {
            if(fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        slow = head;
        fast = fast.next;

        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        ListNode headA = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode headB = new ListNode(-1);

        headA.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        headB.next = node3;
        System.out.println(intersection(headA, headB).next);
        System.out.println(node2.next);
    }
}
