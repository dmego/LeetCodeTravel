package cn.dmego.leetcode.codetop.medium;

import cn.dmego.util.ListNode;

/**
 * 删除链表中倒数第 n 个节点
 * @author dmego
 * @date 2022/01/19 09:21
 */
public class LC_19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 有可能会删除头结点，定义一个哑节点
        ListNode dummy = new ListNode(-1, head);
        // 定义快慢指针，slow 最后指向 倒数 n + 1 个节点，fast 最后 = null
        ListNode slow = dummy, fast = dummy;
        // 计数
        int count = 0;
        while (fast != null) {
            // 当 count = n + 1 时, 才移动 slow
            if (count > n) {
                slow = slow.next;
            }
            fast = fast.next;
            count++;
        }
        // 循环结束，slow 在倒数 n + 1 位置， slow.next 就是 倒数 n 节点
        // 删除 倒数第 n 个节点 也就是 slow.next
        slow.next = slow.next.next;
        return dummy.next;
    }
}
