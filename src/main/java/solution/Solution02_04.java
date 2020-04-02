package solution;

public class Solution02_04 {

/*
 * 输入: head = 3->5->8->5->10->2->1, x = 5
 * 输出: 3->1->2->10->5->5->8
 */

    public static ListNode partition(ListNode head, int x) {
        ListNode lessHead = null, lessP = null;
        ListNode p = head, q = head;
        while (p != null){
            if (p.val >= x){
                q = p;
                p = p.next;
            } else if (p.val < x){
                if (lessHead == null){
                    lessHead = new ListNode(p.val);
                    lessP = lessHead;
                }else{
                    lessP.next = new ListNode(p.val);
                    lessP = lessP.next;
                }
                if (p == head){
                    q = p;
                    head = head.next;
                    p = p.next;
                }else if (p.next != null){
                    q.next = q.next.next;
                    p = p.next;
                }else{
                    q.next = null;
                    p = null;
                }
            }
        }
        System.out.println(head.toString());
        if (lessP == null){
            return head;
        }
        lessP.next = head;
        System.out.println(lessHead.toString());
        return lessHead;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1};
//        int[] array = new int[]{3,5,8,5,10,2,1};
        ListNode head = ListNode.toListByArr(array);
        partition(head, 0);
    }
}
