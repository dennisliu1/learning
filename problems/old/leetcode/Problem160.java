package leetcode;

/**
 * Created by Dennis on 12/1/2017.
 *
 *
 Write a program to find the node at which the intersection of two singly linked lists begins.


 For example, the following two linked lists:

 A:          a1 → a2
 ↘
 c1 → c2 → c3
 ↗
 B:     b1 → b2 → b3
 begin to intersect at node c1.


 Notes:

 If the two linked lists have no intersection at all, return null.
 The linked lists must retain their original structure after the function returns.
 You may assume there are no cycles anywhere in the entire linked structure.
 Your code should preferably run in O(n) time and use only O(1) memory.

 6 minutes - just have to think about the requirements.
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Problem160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lengthA = 0;
        int lengthB = 0;

        ListNode iterA = headA;
        ListNode iterB = headB;
        while(iterA != null) {
            lengthA++;
            iterA = iterA.next;
        }
        while(iterB != null) {
            lengthB++;
            iterB = iterB.next;
        }
        System.out.printf("lengths: %d vs %d\n", lengthA, lengthB);

        iterA = headA;
        iterB = headB;
        if(lengthA > lengthB) {
            int diff = lengthA - lengthB;
            int count = 0;
            while(count < diff && iterA.next != null) {
                iterA = iterA.next;
                count++;
            }
        }
        else if(lengthA < lengthB) {
            int diff = lengthB - lengthA;
            int count = 0;
            while(count < diff && iterB.next != null) {
                iterB = iterB.next;
                count++;
            }
        }

        // same length now to end, try doing match
        while(iterA != null && iterB != null) {
            if(iterA == iterB) return iterA;
            iterA = iterA.next;
            iterB = iterB.next;
        }
        return null;
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
