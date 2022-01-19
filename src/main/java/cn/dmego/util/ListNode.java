package cn.dmego.util;

/**
 * @author dmego
 * @date 2021/09/24 22:54
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {
       StringBuilder buffer = new StringBuilder();
       buffer.append(val).append("->");
       while (next != null) {
           buffer.append(next.val).append("->");
           next = next.next;
       }
       return buffer.toString();
    }

    public static ListNode getByArray(int[] array){
        ListNode head = new ListNode(array[0]);
        ListNode root = head;
        for (int i = 1; i < array.length; i++) {
            root.next = new ListNode(array[i]);
            root = root.next;
        }
        return head;
    }
}
