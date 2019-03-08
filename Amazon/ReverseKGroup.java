package Amazon;

import java.util.List;

public class ReverseKGroup {

    public static ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k <= 0 ) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        head = dummy;

        // head - n1 - n2 - ... - nk - tail
        // head - nk - nk-1 - ... - n1 - tail
        // return n1;
        while(head.next != null) {
            head = reverseNextK(head, k);
        }

        return dummy.next;
    }

    private static ListNode reverseNextK(ListNode head, int k) {
        //check if we have enough nodes to reverse
        ListNode walker = head;
        for(int i = 0; i < k; i++) {
            if(walker.next == null) {
                return walker;
            }
            walker = walker.next;
        }

        ListNode n1 = head.next;
        ListNode pre = head;
        ListNode cur = n1;
        while(k > 0) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
            k--;
        }

        n1.next = cur;
        head.next = pre;

        return n1;
    }

    public static void main(String[] args) {
        ListNode n0 = new ListNode(1);
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(4);
        ListNode n4 = new ListNode(5);

        n0.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        ListNode head = reverseKGroup(n0, 3);
        while(head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
