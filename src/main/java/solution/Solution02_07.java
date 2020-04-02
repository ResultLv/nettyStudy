package solution;

import java.util.ArrayList;
import java.util.List;

public class Solution02_07 {

    /**
     * 链表相交
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        List<ListNode> list = new ArrayList<>();
        while (a != null){
            list.add(a);
            a = a.next;
        }
        while (b != null){
            if (list.contains(b)){
                return b;
            }
            b = b.next;
        }
        return null;
    }

    public static void main(String[] args) {
        int[] a = new int[]{4,1,8,4,5};
        int[] b = new int[]{5,0,1,8,4,5};


    }
}
