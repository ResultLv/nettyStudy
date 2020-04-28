package solution.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StackOfPlates {

    private int cap;
    private List<Stack<Integer>> stackList;

    public StackOfPlates(int cap) {
        this.cap = cap;
        this.stackList = new ArrayList<>();
    }

    public void push(int val) {
        if (cap > 0){
            if (stackList.isEmpty()){
                Stack<Integer> stack = new Stack<>();
                stack.push(val);
                stackList.add(stack);
            }else{
                Stack<Integer> stack = stackList.get(stackList.size() - 1);
                if (stack.size() == cap){
                    stack = new Stack<>();
                    stack.push(val);
                    stackList.add(stack);
                }else{
                    stack.push(val);
                }
            }
        }
    }

    public int pop() {
        if (stackList.isEmpty())
            return -1;
        Stack<Integer> stack = stackList.get(stackList.size() - 1);
        if (stack.size() == 1){
            Integer val = stack.pop();
            stackList.remove(stack);
            return val;
        }
        return stack.pop();
    }

    public int popAt(int index) {
        if (index >= stackList.size())
            return -1;
        Stack<Integer> stack = stackList.get(index);
        if (stack.size() == 1){
            Integer val = stack.pop();
            stackList.remove(stack);
            return val;
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        StackOfPlates stack = new StackOfPlates(1);
        stack.push(7);
        stack.push(3);
        stack.push(7);
        stack.push(8);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
