package solution;

public class ListNode{
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public static ListNode toListByArr(int [] array){
        if (array == null || array.length == 0){
            return null;
        }
        ListNode head = new ListNode(array[0]);
        ListNode next = head;
        for (int i = 1; i < array.length; i++) {
            next.next = new ListNode(array[i]);
            next = next.next;
        }
        return head;
    }

    @Override
    public String toString() {
        ListNode head = this;
        StringBuilder sb = new StringBuilder();
        while (head != null){
            if (head.next != null){
                sb.append(head.val).append("->");
            }else{
                sb.append(head.val);
            }
            head = head.next;
        }
        return sb.toString();
    }
}