package solution.stack;

import java.util.Stack;

/**
 * 面试题 03.02. 栈的最小值
 * 请设计一个栈，除了常规栈支持的pop与push函数以外，还支持min函数，该函数返回栈元素中的最小值。执行push、pop和min操作的时间复杂度必须为O(1)。
 */
class MinStack {

    private int min;
    private Stack<Integer> stack;

    // stack: -2,0,-2,-3
    // min: -3

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        if (x <= min){
            if (!stack.empty()){
                stack.push(min);
            }
            min = x;
        }
        stack.push(x);
    }   

    public void pop() {
        if (stack.empty())
            return;
        if (stack.size() == 1){
            min = Integer.MAX_VALUE;
        }else if (min == stack.peek()){
            stack.pop();
            min = stack.peek();
        }
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(x);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */