package cn.dmego.newcode.top101.listnode;

import cn.dmego.util.ListNode;

/**
 * 删除链表中的重复元素
 * 需要保留一个重复元素
 * @author dmego
 * @date 2022/03/02 09:24
 */
public class BM14 {

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

    public ListNode deleteDuplicates2(ListNode head) {
        // write code here
        if (head == null || head.next == null) return head;
        ListNode root = head;
        while (root != null && root.next != null) {
            if (root.val == root.next.val) {
                ListNode move = root;
                while (move != null && move.val == root.val) {
                    move = move.next;
                }
                root.next = move;
            }
            root = root.next;
        }
        return head;
    }
}
