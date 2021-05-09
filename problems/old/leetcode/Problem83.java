package leetcode;

/**
 * Created by Dennis on 11/20/2017.
 * Given a sorted linked list, delete all duplicates such that each element appear only once.

 For example,
 Given 1->1->2, return 1->2.
 Given 1->1->2->3->3, return 1->2->3.

12 minutes
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Problem83 {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;

        ListNode iter = head;
        ListNode prevIter = head;
        int prevVal = prevIter.val;
        int currVal;
        while(iter != null) {
            currVal = iter.val;
            if(prevVal == currVal) {
                prevIter.next = iter.next;
                if(iter != head) iter.next = null;
                iter = prevIter.next;
            }
            else if(prevVal != currVal) {
                prevIter = iter;
                prevVal = prevIter.val;
                iter = iter.next;
            }
        }
        return head;
    }
    public class ListNode {
        int val;
        ListNode next;ListNode(int x) { val = x; }
    }
}
