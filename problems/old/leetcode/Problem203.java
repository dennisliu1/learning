package leetcode;

/**
 * Created by Dennis on 12/5/2017.
 *
 * Remove all elements from a linked list of integers that have value val.

 Example
 Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
 Return: 1 --> 2 --> 3 --> 4 --> 5

 7 minutes, just edge cases
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Problem203 {

    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return null;

        ListNode curr = head;
        ListNode prev = head;

        while(curr != null && curr.val == val) {
            curr = head.next;
            prev = curr;

            head.next = null;
            head = curr;
        }

        while(curr != null) {
            if(curr.val == val) {
                prev.next = curr.next;
                curr.next = null;
                curr = prev.next;
            }
            else {
                prev = curr;
                curr = curr.next;
            }
        }
        return head;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
