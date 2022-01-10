package cn.dmego.leetcode.codetop.medium;

import cn.dmego.util.ListNode;

/**
 * 反转链表II (反转 [left, right] 这个区间)
 * left 从 1 开始的
 * @author dmego
 * @date 2022/01/10 15:36
 */
public class LC_92 {

    /**

     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode root = dummy;
        // 将链表分为三段 [head, left - 1] [left, right] [right + 1, end]
        int len = 0;
        while (len < left - 1) {
            root = root.next;
            len++;
        }

        ListNode leftPrevNode = root;
        ListNode leftNode = root.next;

        while (len < right) {
            root = root.next;
            len++;
        }
        ListNode rightNode = root;
        ListNode rightNextNode = root.next;

        leftPrevNode.next = null;
        rightNode.next = null;

        leftPrevNode.next = reverse(leftNode);
        leftNode.next = rightNextNode;

        return dummy.next;
    }

    public ListNode reverse(ListNode head) {
        ListNode tmp = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = tmp;
            tmp = head;
            head = next;
        }
        return tmp;
    }
}
