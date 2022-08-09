package cn.dmego.leetcode.solution.lc401_;

import cn.dmego.util.ListNode;

/**
 * 两数相加 II
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 */
public class Solution_445 {
    // 先反转，再相加
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        int flag = 0; // 进位
        while (l1 != null || l2 != null) {
            int val = 0;
            if (l1 != null && l2 != null) {
                val = l1.val + l2.val + flag;
                l1 = l1.next;
                l2 = l2.next;
            } else if (l1 != null) {
                val = l1.val + flag;
                l1 = l1.next;
            } else if (l2 != null) {
                val = l2.val + flag;
                l2 = l2.next;
            }
            flag = val / 10;
            ListNode node = new ListNode(val % 10);
            head.next = node;
            head = head.next;
        }
        if (flag != 0) {
            head.next = new ListNode(flag);
        }
        return reverse(dummy.next);
    }

    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}