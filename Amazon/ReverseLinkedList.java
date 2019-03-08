package Amazon;

import java.util.List;
import java.util.Map;


public class ReverseLinkedList {

    public static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode reversed = reverseUtil(head, null);

        return reversed;
    }

    private static ListNode reverseUtil(ListNode curr, ListNode prev) {
        if(curr == null) {
            return prev;
        }

        ListNode next = curr.next;
        curr.next = prev;
        return reverseUtil(next, curr);
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        System.out.println(reverseList(head));

    }
}
