package solution;

import java.util.Stack;

public class Solution02_02 {

    public static int kthToLast(ListNode head, int k) {

//        Stack<Integer> stack = new Stack<>();
////        while (head != null){
////            stack.push(head.val);
////            head = head.next;
////        }
////        while (k > 1){
////            stack.pop();
////            k--;
////        }
////        return stack.pop();

        /** 快慢双指针*/
        ListNode fast = head;
        while (k > 1){
            fast = fast.next;
            k--;
        }
        while (fast.next != null){
            fast = fast.next;
            head = head.next;
        }
        return head.val;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5};
        ListNode head = ListNode.toListByArr(array);
        System.out.println(Solution02_02.kthToLast(head, 2));
    }
}
