package solution;

import java.util.List;
import java.util.Stack;

public class Solution02_06 {

    /**
     * 编写一个函数，检查输入的链表是否是回文的。
     *
     * 示例 1：
     * 输入： 1->2
     * 输出： false
     *
     * 示例 2：
     * 输入： 1->2->2->1
     * 输出： true
     */
    public static boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev =  null;
        while (fast != null && fast.next != null){
            ListNode temp = slow;
            slow = slow.next;
            fast = fast.next.next;
            temp.next = prev;
            prev = temp;
        }
        if (fast != null){
            slow = slow.next;
        }
        while (slow != null){
            if (slow.val != prev.val){
                return false;
            }
            slow = slow.next;
            prev = prev.next;
        }
        return true;
    }
//    public static boolean isPalindrome1(ListNode head) {
//        int len = 0;
//        ListNode p = head;
//        while (p != null){
//            p = p.next;
//            len++;
//        }
//        Stack<Integer> stack = new Stack<>();
//        int index = 0;
//        while (head != null){
//            if (!stack.empty()){
//                if (len % 2 == 0){
//                    if (stack.peek() == head.val){
//                        stack.pop();
//                    }else {
//                        stack.push(head.val);
//                    }
//                }else {
//                    if (index != len/2){
//                        if (stack.peek() == head.val){
//                            stack.pop();
//                        }else {
//                            stack.push(head.val);
//                        }
//                    }
//                }
//            }else if (len != 1){
//                stack.push(head.val);
//            }
//            head = head.next;
//            index++;
//        }
//       return stack.empty();
//    }

    public static void main(String[] args) {
        int[] array = new int[]{1,2,1,2,1};
        ListNode head = ListNode.toListByArr(array);
        System.out.println(isPalindrome(head));

//        System.out.println(1 & 2 & 1 & 1 & 2 & 1);
    }
}
