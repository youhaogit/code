package Leetcode.p445_AddTwoNumbersII;

import Leetcode.util.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

//    Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
//    Output: 7 -> 8 -> 0 -> 7

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        }else if(l2 == null) {
            return l1;
        }

        Deque<Integer> s1 = new ArrayDeque<>();
        Deque<Integer> s2 = new ArrayDeque<>();
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        int sum = 0;
        ListNode head = new ListNode(0);
        while(!s1.isEmpty() || !s2.isEmpty()) {
            if(!s1.isEmpty()) {
                sum += s1.pop();
            }
            if(!s2.isEmpty()) {
                sum += s2.pop();
            }

            head.val = sum % 10;
            sum /= 10;

            ListNode newNode = new ListNode(sum);
            newNode.next = head;
            head = newNode;
        }

        return head.val == 0 ? head.next : head;
    }
}
