package leetcode;

/**
 * Created by Dennis on 12/1/2017.
 *
 * Given a linked list, determine if it has a cycle in it.

 Follow up:
 Can you solve it without using extra space?

 14 minutes, went straight for the follow up, and reversed true/false
 */
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Problem141 {
    public boolean hasCycle(ListNode head) {
        if(head == null) return false;
        if(head.next == head) return true;
        if(head.next == null) return false;

        ListNode runner = head;
        ListNode walker = head;
        boolean result = false;

        int count = 0;
        while(runner.next != null) {
            runner = runner.next;
            if(count > 2) {
                walker = walker.next;
                count = 0;
            }
            else {
                count++;
            }

            if(runner == walker) {
                result = true;
                break;
            }
        }

        return result;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
