// implement a queue using stack operations.
// 15 minutes, not bad!

class MyQueue {
	LinkedList<Integer> pushStack, popStack;

    /** Initialize your data structure here. */
    public MyQueue() {
        pushStack = new LinkedList<Integer>();
		popStack = new LinkedList<Integer>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        pushStack.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        copyPushToPop();
		if(!popStack.isEmpty()) {
			return popStack.pop();
		}
		else {
			return -1;
		}
    }
    
    /** Get the front element. */
    public int peek() {
        copyPushToPop();
		if(!popStack.isEmpty()) {
			int x = popStack.pop();
			popStack.push(x);
			return x;
		}
		else {
			return -1;
		}
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        boolean empty = true;
		if(!popStack.isEmpty()) empty = false;
		else if(!pushStack.isEmpty()) empty = false;
		return empty;
    }

	private void copyPushToPop() {
		if(popStack.isEmpty()) {
			while(!pushStack.isEmpty()) {
				int x = pushStack.pop();
				popStack.push(x);
			}
		}
	}
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
