package cn.dmego.leetcode.solution.lc101_lc150;

import cn.dmego.util.ListNode;

/**
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 */
public class Solution_141 {

    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == null || fast.next == null) return false;
            if (slow == fast) return true;
        }
        return false;
    }

}