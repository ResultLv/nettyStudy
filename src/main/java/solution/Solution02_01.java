package solution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution02_01 {

    public static ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) return head;
        ListNode temp = head;
        Set<Integer> set= new HashSet<>();
        set.add(temp.val);
        while (temp.next != null){
            if (set.add(temp.next.val)){
                set.add(temp.next.val);
                temp = temp.next;
            }else {
                temp.next = temp.next.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 1, 1, 1, 2};
        ListNode head = ListNode.toListByArr(array);
        System.out.println(removeDuplicateNodes(head));
    }
}
