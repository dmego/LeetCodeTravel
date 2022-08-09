package cn.dmego.leetcode.solution.lc151_lc200;

import cn.dmego.util.ListNode;

/**
 * 相交链表
 * 找到两个链表相加的节点
 */
public class Solution_160 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode head1 = headA;
        ListNode head2 = headB;
        while (head1 != head2) {
            if (head1 == null) {
                head1 = headB;
            } else {
                head1 = head1.next;
            }
            if (head2 == null) {
                head2 = headA;
            } else {
                head2 = head2.next;
            }
        }
        return head1;
    }
}