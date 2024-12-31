package cn.dmego.leetcode.codetop.medium;

import cn.dmego.util.ListNode;

/**
 * 环形链表II （求入环点节点）
 * @author dmego
 * @date 2022/01/10 20:51
 */
public class LC_142 {

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == null || fast.next == null) return null;
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                break;
            }
        }
        return slow;
    }
}
