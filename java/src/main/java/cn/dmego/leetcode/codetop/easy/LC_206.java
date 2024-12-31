package cn.dmego.leetcode.codetop.easy;

import cn.dmego.util.ListNode;

/**
 * 反转链表
 * @author dmego
 * @date 2022/01/04 20:17
 */
public class LC_206 {

    // 迭代写法
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


    // 递归写法
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode node = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }
}
