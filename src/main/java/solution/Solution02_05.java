package solution;

import java.math.BigInteger;
import java.util.logging.Handler;

public class Solution02_05 {

//    输入：(7 -> 1 -> 6) + (5 -> 9 -> 2)，即617 + 295
//    输出：2 -> 1 -> 9，即912

    /**
     * 解法一:普通情况正常，没有考虑到大数问题
     */
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        Long sum = transToNumber1(l1) + transToNumber1(l2);
        ListNode head = numberToNode1(sum);
        return head;
    }

    private static Long transToNumber1(ListNode l1){
        Long res = 0L;
        int count = 0;
        while (l1 != null){
            Double temp = Math.pow(10, count) * l1.val;
            res += temp.longValue();
            l1 = l1.next;
            count++;
        }
        return res;
    }

    private static ListNode numberToNode1(Long number){
        ListNode head = null;
        char[] chars = number.toString().toCharArray();
        ListNode p = head;
        for (int i = chars.length-1; i >=0 ; i--) {
            if (head == null){
                head = new ListNode(Integer.parseInt(String.valueOf(chars[i])));
                p = head;
            }else {
                p.next = new ListNode(Integer.parseInt(String.valueOf(chars[i])));
                p = p.next;
            }
        }
        return head;
    }


    /**
     * 解法二:数位相加
     * 输入：(7 -> 1 -> 6) + (5 -> 9 -> 2)，即617 + 295
     * 输出：2 -> 1 -> 9，即912
     * 7 + 5 = 12 % 10 = 2  div = 1
     * 1 + 9 + 1 = 11 % 10 = 1  div = 1
     * 6 + 2 + 1 = 9 % 10 = 9  div = 0;
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode p = head;
        int addCount = 0;
        while (l1 != null || l2 != null){
            int temp = 0;
            if (l1 != null && l2 != null){
                temp = l1.val + l2.val + addCount;
                addCount = temp / 10;
                l1 = l1.next;
                l2 = l2.next;
            }else if (l1 != null){
                temp = l1.val + addCount;
                addCount = temp / 10;
                l1 = l1.next;
            }else {
                temp = l2.val + addCount;
                addCount = temp / 10;
                l2 = l2.next;
            }
            if (head == null){
                head = new ListNode(temp % 10);
                p = head;
            }else {
                p.next = new ListNode(temp % 10);
                p = p.next;
            }
        }
        if (addCount != 0){
            p.next = new ListNode(addCount);
        }
        return head;
    }

    public static void main(String[] args) {
        int[] array1 = new int[]{7,1,6};
        int[] array2 = new int[]{};
        ListNode l1 = ListNode.toListByArr(array1);
        ListNode l2 = ListNode.toListByArr(array2);
        ListNode head = addTwoNumbers(l1, l2);
        System.out.println(head.toString());
    }
}
