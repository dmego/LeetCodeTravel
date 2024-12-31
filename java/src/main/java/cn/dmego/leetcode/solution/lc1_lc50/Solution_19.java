package cn.dmego.leetcode.solution.lc1_lc50;

import cn.dmego.util.ListNode;

/**
 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

 进阶：你能尝试使用一趟扫描实现吗？
 */
public class Solution_19 {

    /**
        使用两个指针，第一个指针 fast 先出发，走 n - 1 步，第二个指针 slow 再从头节点出发，当 fast = null 时，slow 节点的位置刚好是倒数 n 节点的前一个节点
        也就是倒数第 n - 1 节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;
        int index = 0;
        while (fast != null) {
            // 从 dummy 开始，从 0 ~ n-1, slow 最后来到倒数第 n-1的位置
            if (index > n) {
                slow = slow.next;
            }
            fast = fast.next;
            index++;
        }
        // 删除 slow 的下一个节点，也就是倒数第 n 个节点
        ListNode next = slow.next;
        slow.next = next.next;
        next.next = null;
        return dummy.next;
    }

}
