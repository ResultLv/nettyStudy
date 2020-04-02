package solution;

import java.util.HashSet;
import java.util.Set;

public class Solution02_08 {

    /**
     * 环路检测 1->2->3->4->5 => 2
     * 慢 1,2,3,4  5,2
     * 快 1,2,3,4,5,2,3,4  1,2
     * @param head
     * @return
     */
    public static ListNode detectCycle(ListNode head) {
        boolean flag = true;
        Set<ListNode> set = new HashSet<>();
        while (head != null && flag){
            if (set.add(head)){
                head = head.next;
            }else {
                flag = false;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        int[] array = new int[]{3,2,0,-4};
        ListNode head = ListNode.toListByArr(array);
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = head.next;
        ListNode node = detectCycle(head);
        System.out.println(node.val);
//        System.out.println(node.toString());
    }
}
