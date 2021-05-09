package leetcode;

/**
 * Created by Dennis on 12/6/2017.
 *
 * Implement the following operations of a stack using queues.

 push(x) -- Push element x onto stack.
 pop() -- Removes the element on top of the stack.
 top() -- Get the top element.
 empty() -- Return whether the stack is empty.
 Notes:
 You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
 Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
 You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).

 8 minutes
 */

import java.util.LinkedList;

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
public class Problem225 {
    LinkedList<Integer> queue;

    /** Initialize your data structure here. */
    public Problem225() {
        constructor();
    }

    public void constructor() {
        queue = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.add((Integer) x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if(queue.isEmpty()) return -1;

        for(int i = 0; i < queue.size()-1; i++) {
            int x = queue.removeFirst();
            push(x);
        }
        return queue.removeFirst();
    }

    /** Get the top element. */
    public int top() {
        if(queue.isEmpty()) return -1;

        for(int i = 0; i < queue.size()-1; i++) {
            int x = queue.removeFirst();
            push(x);
        }
        int x = queue.removeFirst();
        queue.add(x);
        return x;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}
