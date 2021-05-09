/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * 
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * 
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int num1 = parseListNodeReverse(L1);
        int num2 = parseListNodeReverse(L2);
        return createListNode(num1+num2);
    }

    public int[] parseListNodeReverse(ListNode nodes) {
    	int result = 0;
    	int length = 0;

    	ListNode list = nodes;
    	while(list != null) {
    		if(length > 0) result *= 10;

    		result += nodes.val;

    		list = nodes.next;
    		length++;
    	}
    	return result;
    }

    public createListNode(int number) {

    }
}

