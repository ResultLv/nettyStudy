package solution;

public class Solution02_03 {

    public static ListNode deleteNode(ListNode node, ListNode head) {
        node.val = node.next.val;
        node.next = node.next.next;
        System.out.println(node);
        return head;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5};
        ListNode head = ListNode.toListByArr(array);
        ListNode node = head.next.next;
        System.out.println(Solution02_03.deleteNode(node, head));
    }
}
