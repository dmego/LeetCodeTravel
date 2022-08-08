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
       ListNode curr = next;
       while (curr != null) {
           buffer.append(curr.val).append("->");
           curr = curr.next;
       }
       return buffer.toString();
    }

    public static ListNode getByArray(int[] array){
        ListNode dummy = new ListNode(0);
        ListNode root = dummy;
        for (int i : array) {
            root.next = new ListNode(i);
            root = root.next;
        }
        return dummy.next;
    }
}
