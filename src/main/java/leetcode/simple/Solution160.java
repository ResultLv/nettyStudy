package leetcode.simple;

import solution.ListNode;

import java.util.Stack;

/**
 * 相交链表
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 * @author Result Lv
 * @date 2021/4/16 10:55 下午
 */
public class Solution160 {

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Stack<ListNode> stackA = new Stack<>();
        Stack<ListNode> stackB = new Stack<>();
        while (headA != null){
            stackA.push(headA);
            headA = headA.next;
        }
        while (headB != null){
            stackB.push(headB);
            headB = headB.next;
        }
        ListNode meetNode = null;
        while (stackA.size() > 0 && stackB.size() > 0){
            if (stackA.peek() == stackB.peek()){
                meetNode = stackA.peek();
            }
            stackA.pop();
            stackB.pop();
        }
        return meetNode;
    }
    
    public static void main(String[] args){
        ListNode headA = ListNode.toListByArr(new int[]{4, 1, 8, 4, 5});
        ListNode headB = ListNode.toListByArr(new int[]{5, 0, 1});
        headB.next.next.next = headA.next.next;

        System.out.println(getIntersectionNode(headA, headB));
    }
}
