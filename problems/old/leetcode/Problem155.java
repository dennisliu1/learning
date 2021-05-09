package leetcode;

/**
 * Created by Dennis on 12/1/2017.
 *
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

 push(x) -- Push element x onto stack.
 pop() -- Removes the element on top of the stack.
 top() -- Get the top element.
 getMin() -- Retrieve the minimum element in the stack.
 Example:
 MinStack minStack = new MinStack();
 minStack.push(-2);
 minStack.push(0);
 minStack.push(-3);
 minStack.getMin();   --> Returns -3.
 minStack.pop();
 minStack.top();      --> Returns 0.
 minStack.getMin();   --> Returns -2.

 45+ 8 minutes, struggled to get a nice solution, turns out its greedy
 */

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
public class Problem155 {
    LinkedList<Node> stack;

    /** initialize your data structure here. */
    public Problem155() {
        constructor();
    }

    public void constructor() {
        stack = new LinkedList<>();
    }

    public void push(int x) {
        if(stack.isEmpty()) {
            stack.push(new Node(x, x));
        }
        else {
            stack.push(new Node(x, Math.min(stack.peek().min, x)));
        }
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek().value;
    }

    public int getMin() {
        return stack.peek().min;
    }

    class Node {
        int value;
        int min;

        Node(int value, int min) {
            this.value = value;
            this.min = min;
        }
    }

    public static void main(String[] args) {
        //["MinStack","push","push","push","top","pop","getMin","pop","getMin","pop","push","top","getMin","push","top","getMin","pop","getMin"]
        //[[],[2147483646],[2147483646],[2147483647],[],[],[],[],[],[],[2147483647],[],[],[-2147483648],[],[],[],[]]
        Problem155 p = new Problem155();
        p.push(2147483646);
        p.push(2147483646);
        p.push(2147483647);
        int a1 = p.top();
        p.pop();
        int a2 = p.getMin();
        p.pop();
        int a3 = p.getMin();
        p.pop();
        p.push(2147483647);
        int a4 = p.top();
        int a5 = p.getMin();
        p.push(-2147483648);
        p.top();
        p.getMin();
        p.pop();
        p.getMin();
    }
}
