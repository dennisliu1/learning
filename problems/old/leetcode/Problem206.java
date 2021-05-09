package leetcode;

/**
 * Created by Dennis on 12/6/2017.
 *
 * Reverse a singly linked list.
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 *
 * 5 minutes , i knew the answer
 */
public class Problem206 {
    public ListNode reverseList(ListNode head) {
        if(head == null) return null;

        ListNode curr = head;
        ListNode next = curr.next;
        head.next = null;

        while(next != null) {
            ListNode temp = next.next;
            next.next = curr;
            curr = next;
            next = temp;
        }

        return curr;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
