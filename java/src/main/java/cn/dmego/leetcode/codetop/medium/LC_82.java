package cn.dmego.leetcode.codetop.medium;

import cn.dmego.util.ListNode;

/**
 * @author dmego
 * @date 2022/01/25 18:10
 */
public class LC_82 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode slow = dummy, fast = head;
        while (fast != null) {
            ListNode next = fast.next;
            while (next != null && fast.val == next.val) {
                next = next.next;
            }
            fast = next;
            if (fast.next != null && fast.val != fast.next.val) {
                slow.next = fast;
                slow = slow.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node41 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node41;
        node41.next = node5;
        LC_82 s = new LC_82();
        s.deleteDuplicates(head);
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
    }
}
