package cn.dmego.leetcode.solution.lc51_lc100;

import cn.dmego.util.ListNode;

/**
 * 删除链表中的重复元素
 * 重复的元素只需要保留一个
 * @author dmego
 * @date 2022/03/02 09:40
 */
public class Solution_83 {

    public ListNode deleteDuplicates(ListNode head) {
        // 如果链表为null或者只有一个节点，直接返回
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            // cur 和 cur.next 的重复的元素
            if (cur.val == cur.next.val) {
                // 删除 cur.next
                cur.next = cur.next.next;
            } else {
                // 否则移动 cur
                cur = cur.next;
            }
        }
        // 直接返回头结点
        return head;
    }
}
