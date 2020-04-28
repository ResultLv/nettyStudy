package solution.stack;

import java.util.Stack;

public class MyQueue {

    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    /** Initialize your data structure here. */
    public MyQueue() {
        this.stack1 = new Stack<>();
        this.stack2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        while (!stack2.empty()){
            stack1.push(stack2.pop());
        }
        stack1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        while (!stack1.empty()){
            stack2.push(stack1.pop());
        }
        if (!stack2.empty()){
            return stack2.pop();
        }
        return -1;
    }

    /** Get the front element. */
    public int peek() {
        while (!stack1.empty()){
            stack2.push(stack1.pop());
        }
        if (!stack2.empty()){
            return stack2.peek();
        }
        return -1;
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.empty() && stack2.empty();
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        System.out.println(myQueue.pop());
        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(3);
        System.out.println(myQueue.pop());
        myQueue.push(4);
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
    }
}
