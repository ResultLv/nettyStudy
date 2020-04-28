package solution.stack;

import java.util.Comparator;
import java.util.Stack;

public class SortedStack {

    private Stack<Integer> stack;
    private Stack<Integer> tempStack;

    public SortedStack() {
        this.stack = new Stack<>();
        this.tempStack =  new Stack<>();
    }

    public void push(int val) {
        if (stack.empty()){
            stack.push(val);
        }else {
            if (val > stack.peek()){
                while (!stack.empty() && val > stack.peek()){
                    tempStack.push(stack.pop());
                }
                tempStack.push(val);
            }else{
                stack.push(val);
            }
            while (!tempStack.empty()){
                stack.push(tempStack.pop());
            }
        }
    }

    public void pop() {
        if (!stack.empty()){
            stack.pop();
        }
    }

    public int peek() {
        if (stack.empty()){
            return -1;
        }
        return stack.peek();
    }

    public boolean isEmpty() {
        return stack.empty();
    }

    public static void main(String[] args) {
        SortedStack stack = new SortedStack();
        stack.push(2);
        stack.push(1);
        stack.push(8);
        stack.push(3);
        stack.push(0);
        stack.push(4);
        System.out.println(stack);
    }
}
