package cn.dmego.leetcode;

import cn.dmego.leetcode.util.ListNode;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 进阶：
 *
 *
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 */
public class Solution_25 {
    /**
     1. 把链表分为已反转，待反转，未反转三部分。
     2. 每次反转的区间长度是 k, 所以我们必须循环遍历链表，每次遍历k个长度后，停下反转链表。
     3. 如果在遍历链表过程中，发现 还没遍历 k 个长度，就到链表末尾了，那么直接返回。
     4. 初始化两个节点 pre：待反转链表的前驱；end: 待反转链表的末尾(end.next 就是待反转链表的后继)
     5. 每次反转链表后，pre 和 end 重置为待反转链表的前驱节点(初始时都执行head的前驱-哑节点)
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy, end = dummy;

        // 大循环 里 每次走 k 步，切断要反转的链表，反转之后再拼接回去
        while (end != null) {
            for (int i = 0; i < k; i++) {
                end = end.next;
                // 这里判断放下面，因为如果 end = null，恰好循环结束，下面取 end.next 会 NPE
                if (end == null) {
                    return dummy.next;
                }
            }
            ListNode curr = pre.next; // 待反转链表的头结点(也是反转之后链表的尾结点)
            ListNode next = end.next; // 带反转链表的后置节点

            // 切断链表
            pre.next = null;
            end.next = null;

            // 反转子链表
            ListNode start = reverse(curr);

            // 拼接链表
            pre.next = start;
            curr.next = next;

            // 重置 end 和 pre 节点，指向 下一个待反转子链表的前驱节点(也是上一个反转之后链表的尾结点)
            pre = curr;
            end = curr;

        }
        return dummy.next;
    }

    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = pre;
            pre = head;
            head = tmp;
        }
        return pre;
    }
}
