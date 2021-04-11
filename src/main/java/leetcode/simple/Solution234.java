package leetcode.simple;

import solution.ListNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 回文链表
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 * @author Result Lv
 * @date 2021/4/11 2:00 下午
 */
public class Solution234 {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     *
     * 输入: 1->2
     * 输出: false
     *
     * 输入: 1->2->2->1
     * 输出: true
     */
    /*栈解法*/
    public static boolean isPalindrome(ListNode head) {
        LinkedList<Integer> stack = new LinkedList<>();
        ListNode tmp = head;
        while(tmp != null){
            stack.push(tmp.val);
            tmp = tmp.next;
        }
        ListNode tmp2 = head;
        while(tmp2 != null){
            if(tmp2.val != stack.pop()){
                return false;
            }
            tmp2 = tmp2.next;
        }
        return true;
    }

    /*快慢指针法*/
    public static boolean isPalindrome1(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow;
        while (mid.next != null){
            stack.push(mid.next.val);
            mid = mid.next;
        }
        ListNode p = head;
        while (!stack.empty()){
            if (p.val != stack.pop()){
                return false;
            }
            p = p.next;
        }
        return true;
    }

    /*复制到List后用快慢指针法*/
    public static boolean isPalindrome2(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null){
            list.add(cur.val);
            cur = cur.next;
        }
        int start = 0, end = list.size() - 1;
        while (start < end){
            if (!list.get(start).equals(list.get(end))){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static void main(String[] args){
        int[] nums = new int[]{1,0,1};
        ListNode head = ListNode.toListByArr(nums);
        System.out.println(isPalindrome(head));
        System.out.println(isPalindrome1(head));
        System.out.println(isPalindrome2(head));
    }
}
